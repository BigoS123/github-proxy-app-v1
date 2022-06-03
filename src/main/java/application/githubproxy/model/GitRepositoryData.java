package application.githubproxy.model;

import java.util.ArrayList;
import java.util.concurrent.Future;

public class GitRepositoryData {

    //repository data
private String repositoryName;
private String repositoryOwner;
private Future<ArrayList<RepositoryBranchData>> repositoryBranchData;

    public GitRepositoryData(String repositoryName, String repositoryOwner, Future<ArrayList<RepositoryBranchData>> repositoryBranchData) {
        this.repositoryName = repositoryName;
        this.repositoryOwner = repositoryOwner;
        this.repositoryBranchData = repositoryBranchData;
    }

    public Future<ArrayList<RepositoryBranchData>> getRepositoryBranchData() {
        return repositoryBranchData;
    }

    public void setRepositoryBranchData(Future<ArrayList<RepositoryBranchData>> repositoryBranchData) {
        this.repositoryBranchData = repositoryBranchData;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryOwner() {
        return repositoryOwner;
    }

    public void setRepositoryOwner(String repositoryOwner) {
        this.repositoryOwner = repositoryOwner;
    }

}
