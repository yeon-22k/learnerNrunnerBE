package com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception;

import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //GlobalErrorCode
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<String>> handleAllException(Exception e) {
        log.error(">>>>> Internal Server Error : ", e);
        ErrorResponseCode errorCode = GlobalErrorCode.INTERNAL_SERVER_ERROR;
        ApiResponse<String> errorResponse = ApiResponse.onFailure(errorCode);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ApiResponse<Void>> handleIntegrityConstraint(DataIntegrityViolationException e) {
        log.warn(">>>>> Data Integrity Violation Exception : {}", e.getMessage());
        ErrorResponseCode errorCode = GlobalErrorCode.DATA_INTEGRITY_VIOLATION;
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ErrorResponseCode errorCode = GlobalErrorCode.VALIDATION_ERROR;
        // 실패한 validation 을 담을 Map
        Map<String, String> failedValidations = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        // fieldErrors 를 순회하며 failedValidations 에 담는다.
        fieldErrors.forEach(error -> failedValidations.put(error.getField(), error.getDefaultMessage()));
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(errorCode, failedValidations);
        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

//    //보안 관련
//    @ExceptionHandler(NoResourceFoundException.class)
//    public ResponseEntity<ApiResponse<String>> handleNoResourceFoundException(NoResourceFoundException e) {
//        log.warn(">>>>> No static resource : {}", e.getMessage());
//        ErrorResponseCode errorCode = GlobalErrorCode.NOT_FOUND;
//        ApiResponse<String> errorResponse = ApiResponse.onFailure(errorCode);
//        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
//    }

    //ErrorCode
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e){
        log.warn(">>>>> Custom Exception: {}", e.getMessage());
        ErrorResponseCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ApiResponse.onFailure(errorCode));
    }
}
