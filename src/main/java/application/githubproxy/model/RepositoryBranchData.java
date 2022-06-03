package application.githubproxy.model;

public class RepositoryBranchData {

//last commit sha and branch name

    String branchName;
    String lastCommitSHA;

    public RepositoryBranchData(String name, String lastCommitSHA) {
        this.branchName = name;
        this.lastCommitSHA = lastCommitSHA;
    }

    public String getName() {
        return branchName;
    }

    public void setName(String name) {
        this.branchName = name;
    }

    public String getLastCommitSHA() {
        return lastCommitSHA;
    }

    public void setLastCommitSHA(String lastCommitSHA) {
        this.lastCommitSHA = lastCommitSHA;
    }
}
