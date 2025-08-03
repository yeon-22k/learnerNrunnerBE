package com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception;

import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e){
        log.warn(">>>>> Custom Exception: {}", e.getMessage());
        ErrorResponseCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ApiResponse.onFailure(errorCode));
    }
}
