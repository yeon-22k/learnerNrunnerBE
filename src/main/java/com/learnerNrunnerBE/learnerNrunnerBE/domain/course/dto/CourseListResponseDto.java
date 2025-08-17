package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CourseListResponseDto {
    private List<CourseListItem> result;

    @Getter
    @NoArgsConstructor
    public static class CourseListItem{
        @JsonProperty("id")
        private String id;
    }
}
