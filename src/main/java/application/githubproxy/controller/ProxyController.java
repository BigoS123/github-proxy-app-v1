package application.githubproxy.controller;

import application.githubproxy.model.GitRepositoryData;
import application.githubproxy.model.RepositoryBranchData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/")
public class ProxyController{

    private RestTemplate restTemplate = new RestTemplate();

    //get all github repositories for given user and return GitRepositoryData list
    @Async
    @GetMapping(value = "userrepo")
    public CompletableFuture<ResponseEntity> getGithubRepositories(@RequestParam(name = "username") String username){

        ArrayList<GitRepositoryData> userRepositories = new ArrayList<>();

        //get repository request
        try{
        ResponseEntity<String> repositoryResponseData =  restTemplate.getForEntity("https://api.github.com/users/"+username+"/repos", String.class);
            //parse data to JSONArray
            JSONArray repositoryArrayJsonData = new JSONArray(repositoryResponseData.getBody());
            repositoryArrayJsonData.forEach(repository -> {

                JSONObject repositoryJsonObj = new JSONObject(repository.toString());

                //check if repository is forked - if not get branch and commit data and add element to array
                if(!repositoryJsonObj.getBoolean("fork")) {
                    String repositoryName = repositoryJsonObj.get("name").toString();
                    try {
                        userRepositories.add(new GitRepositoryData(repositoryName,
                                repositoryJsonObj.getJSONObject("owner").getString("login"),
                                getBranchesArrayWithLastCommitSHA(repositoryName, username).get() ));
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

            });
            return  CompletableFuture.completedFuture(ResponseEntity.ok(userRepositories));

     } catch(HttpStatusCodeException e) {

            Map<String,Object> responseBody = new HashMap<>();
            responseBody.put("message", new JSONObject(e.getResponseBodyAsString()).getString("message"));
            responseBody.put("status", e.getStatusCode().value());

            return  CompletableFuture.completedFuture(new ResponseEntity(responseBody, HttpStatus.valueOf(e.getStatusCode().value())));
    }

    }

    //get data for each branch of repository asynchronously
    @Async
    public CompletableFuture< ArrayList<RepositoryBranchData> > getBranchesArrayWithLastCommitSHA(String repositoryName, String username) throws InterruptedException {
        ArrayList<RepositoryBranchData> branchDataArray = new ArrayList<>();
        ResponseEntity<String> repositoryBranchesResponseData =  restTemplate.getForEntity("https://api.github.com/repos/"+username+"/"+repositoryName+"/branches", String.class);
        JSONArray repositoryBranchJsonDataArray = new JSONArray(repositoryBranchesResponseData.getBody());
        repositoryBranchJsonDataArray.forEach(branch -> {
            JSONObject branchJsonObject = new JSONObject(branch.toString());
            branchDataArray.add(new RepositoryBranchData(branchJsonObject.getString("name"), branchJsonObject.getJSONObject("commit").getString("sha")));
        });
        Thread.sleep(1);
        return CompletableFuture.completedFuture(branchDataArray);
    }


    //handle incorrect 'accept' header
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity handleHttpMediaTypeNotAcceptableException() {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\": \" 'Accept: application/json' header required\", \"status\": 406}");    }



}
