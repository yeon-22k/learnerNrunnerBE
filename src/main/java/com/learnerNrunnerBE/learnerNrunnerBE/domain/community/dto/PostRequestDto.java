package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.entity.Post;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostRequestDto {
    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("is_Study")
    private Boolean isStudy;

}
