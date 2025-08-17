package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.dto;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.document.CourseDocument;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseSearchResponseDto {
    private final Long id;
    private final String title;
    private final String description;
    private final String imageUrl;
    private final String courseUrl;
    private final String provider;
    private final String teachers;

    // CourseDocument(내부 레시피)를 DTO(메뉴판)로 변환하는 정적 팩토리 메서드 (필요한것만 추출하는 의미에서 변환)
    public static CourseSearchResponseDto from(CourseDocument document) {
        return CourseSearchResponseDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription()) // 필요하다면 100자로 자르는 등의 가공도 가능
                .imageUrl(document.getImageUrl())
                .courseUrl(document.getCourseUrl())
                .provider(document.getProvider())
                .teachers(document.getTeachers())
                .build();
    }
}
