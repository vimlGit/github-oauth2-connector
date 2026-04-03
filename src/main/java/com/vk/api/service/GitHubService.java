package com.vk.api.service;

import com.vk.api.dto.CreateIssueRequest;
import com.vk.api.dto.IssueResponse;
import com.vk.api.dto.RepoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GitHubService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.github.com")
            .build();

    public List<RepoDTO> getRepos(String token) {
        return webClient.get()
                .uri("/user/repos")
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToFlux(RepoDTO.class)
                .collectList()
                .block();
    }

    public IssueResponse createIssue(String token, CreateIssueRequest request) {

        return webClient.post()
                .uri("/repos/" + request.getOwner() + "/" + request.getRepo() + "/issues")
                .headers(h -> h.setBearerAuth(token))
                .bodyValue(Map.of(
                        "title", request.getTitle(),
                        "body", request.getBody()
                ))
                .retrieve()
                .bodyToMono(IssueResponse.class)
                .block();
    }

    public List<IssueResponse> listIssues(String token, String owner, String repo) {

        return webClient.get()
                .uri("/repos/" + owner + "/" + repo + "/issues")
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToFlux(IssueResponse.class)
                .collectList()
                .block();
    }
}
