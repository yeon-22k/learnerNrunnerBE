package com.learnerNrunnerBE.learnerNrunnerBE.global.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code", "message", "result"})
public class ApiResponse<T> {
    private final String code;
    private final String message;
    private T result;

    public static <T> ApiResponse<T> onSuccess(SuccessResponseCode successCode, T result) {
        return new ApiResponse<>(successCode.getCode(), successCode.getMessage(), result);
    }

    public static <T> ApiResponse<T> onSuccess(SuccessResponseCode successCode) {
        return new ApiResponse<>(successCode.getCode(), successCode.getMessage(), null);
    }

    // valid 관련 exception 에서만 사용
    public static <T> ApiResponse<T> onFailure(ErrorResponseCode errorCode, T result) {
        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(ErrorResponseCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
}
