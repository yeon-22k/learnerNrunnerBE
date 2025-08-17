package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    private String provider;

    @Column(columnDefinition = "TEXT")
    private String tag;

    // indexdAt은 BaseTimeEntity의 updatedAt으로 대체 가능 > 생성하자
// ** 후에 추가
//    @OneToMany(mappedBy = "course")
//    private List<StudyRoom> studyRooms = new ArrayList<>();
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<Favorite> favorites = new ArrayList<>();
//
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningHistory> learningHistories;

    //아래는 효율적 ES위해 추가
    @Column(name = "image_url")
    private String imageUrl; // 강의 이미지 URL

    @Column(name = "course_url")
    private String courseUrl; // 실제 강의로 연결되는 URL

    private String effort; // 주당 학습 시간

    private Integer weeks; // 총 주차

    private String teachers; // 교수진

}
