package application.githubproxy.model;

import java.util.ArrayList;
import java.util.concurrent.Future;

public class GitRepositoryData {

    //repository data
private String repositoryName;
private String repositoryOwner;
private ArrayList<RepositoryBranchData> repositoryBranchData;

    public GitRepositoryData(String repositoryName, String repositoryOwner, ArrayList<RepositoryBranchData> repositoryBranchDataArray) {
        this.repositoryName = repositoryName;
        this.repositoryOwner = repositoryOwner;
        this.repositoryBranchData = repositoryBranchDataArray;
    }

    public ArrayList<RepositoryBranchData> getRepositoryBranchData() {
        return repositoryBranchData;
    }

    public void setRepositoryBranchData(ArrayList<RepositoryBranchData> repositoryBranchDataArray) {
        this.repositoryBranchData = repositoryBranchDataArray;
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
