package com.explicit.redditCloneBackend.Config.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    private String subredditName;
    @NotBlank(message = "post name can not be null")
    private String postName;
    private String url;
    private String description;
}
