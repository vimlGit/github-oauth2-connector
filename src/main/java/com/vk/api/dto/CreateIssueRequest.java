package com.vk.api.dto;

import lombok.Data;

@Data
public class CreateIssueRequest {
    private String owner;
    private String repo;
    private String title;
    private String body;
}
