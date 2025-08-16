package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.document;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Builder
@Document(indexName = "course")
@Setting(settingPath = "elasticsearch/nori-settings.json")
public class CourseDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer") // 내용 기반 검색 (한글 분석)
    private String title;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String description;

    @Field(type = FieldType.Keyword) // 정확한 값 필터링
    private String category;

    @Field(type = FieldType.Keyword)
    private String provider;

    @Field(type = FieldType.Keyword)
    private String teachers;

    @Field(type = FieldType.Text) // 이미지나 URL은 검색 대상이 아니므로 text로 충분
    private String imageUrl;

    @Field(type = FieldType.Text)
    private String courseUrl;

    @Field(type = FieldType.Keyword) // 추천 시스템의 핵심 필드
    private List<String> tags;

    public static CourseDocument from(Course course) { //course 엔티티를 document로 변환하는 의미
        // category, provider, tag 필드를 합쳐서 tags 리스트 생성 ** 추후 고도화 필요
        List<String> combinedTags = Stream.of(course.getCategory(), course.getProvider(), course.getTag())
                .filter(s -> s != null && !s.isBlank())
                .distinct()
                .toList();

        return CourseDocument.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .category(course.getCategory())
                .provider(course.getProvider())
                .teachers(course.getTeachers())
                .imageUrl(course.getImageUrl())
                .courseUrl(course.getCourseUrl())
                .tags(combinedTags)
                .build();
    }
}
