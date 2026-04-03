package com.vk.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RepoDTO {
    private Long id;
    private String name;
    private String full_name;

    @JsonProperty("private")
    private boolean privateRepo;
}
