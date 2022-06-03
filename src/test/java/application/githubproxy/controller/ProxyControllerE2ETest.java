package application.githubproxy.controller;

import application.githubproxy.model.GitRepositoryData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProxyControllerE2ETest {

    @LocalServerPort
    private int port;
    private String username = "BigoS123";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    @DisplayName("check if status 200")
    void httpGet_returns_user_repositories() {

        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:"+port+"/api/v1/userrepo?username="+username, String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("check if status 404")
    void httpGet_returns_user_notFount() {
        try {
            ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/userrepo?username=testdummytext" + username, String.class);
        }catch (HttpStatusCodeException e){
            assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}