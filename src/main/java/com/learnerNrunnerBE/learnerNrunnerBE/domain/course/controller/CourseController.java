package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.controller;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service.CourseService;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/fetch-courses")
    public ResponseEntity<ApiResponse<Void>> fetchCourses() {
        int savedCount = courseService.fetchAndSaveKmoocCourses();
        log.info("savedCount={}", savedCount);

        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK));
    }

}
