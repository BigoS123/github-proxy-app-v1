package application.githubproxy.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;


import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;


class ProxyControllerMethodTest {

    @Test
    @DisplayName("Should throw 404 not found when user was not found")
    void getGithubRepositories_Throws404_on_user_not_found() {
        var mockProxyController = mock(ProxyController.class);
        when(mockProxyController.getGithubRepositories("NonExistingUsername1234567")).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.NOT_FOUND)));
    }

    @Test
    @DisplayName("Should return status 200 when user was found")
    void getGithubRepositories_Throws200_on_user_found() {
        var mockProxyController = mock(ProxyController.class);
        when(mockProxyController.getGithubRepositories("BigoS123")).thenReturn(CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.OK)));
    }

    @Test
    @DisplayName("Should return status 200 when user was found")
    void getGithubRepositories_Throws200_on_user_found1() throws InterruptedException {
        var mockProxyController = mock(ProxyController.class);
        when(mockProxyController.getBranchesArrayWithLastCommitSHA("nonexistingrepo","BigoS123")).thenReturn(null);
    }

}