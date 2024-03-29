package com.explicit.redditCloneProject.Config.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubredditDto {

    private Long id;
    @NotBlank(message = "Enter a name")
    private String subredditName;
    @NotBlank(message = "description is required")
    private String description;
    private Integer numberOfPost;
}
