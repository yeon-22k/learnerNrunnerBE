package com.learnerNrunnerBE.learnerNrunnerBE.domain.community.controller;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.dto.CommentResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.community.service.CommentService;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;

    @GetMapping("/{post_id}/comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getComments() {


    }

    @PostMapping("/fetch-courses")
    public ResponseEntity<ApiResponse<Void>> fetchCourses() {
        int savedCount = courseService.fetchAndSaveKmoocCourses();
        log.info("savedCount={}", savedCount);

        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.OK));
    }
}
