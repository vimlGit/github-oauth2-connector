package com.vk.api.controller;

import com.vk.api.dto.CreateIssueRequest;
import com.vk.api.dto.IssueResponse;
import com.vk.api.dto.RepoDTO;
import com.vk.api.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class GitHubController {


    @Autowired
    private GitHubService gitHubService;
    @Autowired
    private OAuth2AuthorizedClientService clientService;

    @GetMapping("/user")
    public Map<String, Object> getUser(OAuth2AuthenticationToken auth) {
        OAuth2AuthorizedClient client =
                clientService.loadAuthorizedClient(
                        auth.getAuthorizedClientRegistrationId(),
                        auth.getName()
                );

        String accessToken = client.getAccessToken().getTokenValue();

        return Map.of(
                "username", auth.getPrincipal().getAttributes().get("login"),
                "token", accessToken
        );
    }

    @GetMapping("/repos")
    public List<RepoDTO> getRepos(OAuth2AuthenticationToken auth) {

        OAuth2AuthorizedClient client =
                clientService.loadAuthorizedClient(
                        auth.getAuthorizedClientRegistrationId(),
                        auth.getName()
                );

        String token = client.getAccessToken().getTokenValue();

        return gitHubService.getRepos(token);
    }

    @PostMapping("/issues")
    public IssueResponse createIssue(@RequestBody CreateIssueRequest request,
                                     OAuth2AuthenticationToken auth) {

        OAuth2AuthorizedClient client =
                clientService.loadAuthorizedClient(
                        auth.getAuthorizedClientRegistrationId(),
                        auth.getName()
                );

        String token = client.getAccessToken().getTokenValue();

        return gitHubService.createIssue(token, request);
    }

    @GetMapping("/issues")
    public List<IssueResponse> getIssues(@RequestParam String owner,
                                    @RequestParam String repo,
                                    OAuth2AuthenticationToken auth) {

        OAuth2AuthorizedClient client =
                clientService.loadAuthorizedClient(
                        auth.getAuthorizedClientRegistrationId(),
                        auth.getName()
                );

        String token = client.getAccessToken().getTokenValue();

        return gitHubService.listIssues(token, owner, repo);
    }
}
