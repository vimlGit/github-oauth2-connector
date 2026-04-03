package com.vk.api.dto;

import lombok.Data;

@Data
public class IssueResponse {
    private Long id;
    private String title;
    private String state;
    private String html_url;
}
