package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.controller;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.dto.CourseSearchResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.service.CourseSearchService;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseSearchController {
    private final CourseSearchService courseSearchService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CourseSearchResponseDto>>> searchCourses (@RequestParam String keyword) {
        List<CourseSearchResponseDto> result = courseSearchService.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK, result));
    }

}
