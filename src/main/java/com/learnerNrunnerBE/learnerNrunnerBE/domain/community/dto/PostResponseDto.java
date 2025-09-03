package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import lombok.Builder;

@Builder
public class PostResponseDto {
    @JsonProperty("post_id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("is_Study")
    private Boolean isStudy;

    public static PostResponseDto fromEntity(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .isStudy(post.getIsStudy())
                .build();
    }
}
