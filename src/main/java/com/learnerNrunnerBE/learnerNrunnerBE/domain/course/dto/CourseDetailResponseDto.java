package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;

public class CourseDetailResponseDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("classfy_name")
    private String classfyName;

    @JsonProperty("org_name")
    private String orgName;

    @JsonProperty("teachers")
    private String teachers;

    @JsonProperty("course_url")
    private String courseUrl;

    @JsonProperty("media_url") // API 명세 상 이미지 URL 필드
    private String imageUrl;

    @JsonProperty("effort")
    private String effort;

    @JsonProperty("weeks")
    private Integer weeks;

    public Course toEntity(){
        return Course.builder()
                .title(this.name)
                .description(this.overview)
                .category(this.classfyName)
                .provider(this.orgName)
                .teachers(this.teachers)
                .courseUrl(this.courseUrl)
                .imageUrl(this.imageUrl)
                .tag(this.classfyName).build();
    }

}
