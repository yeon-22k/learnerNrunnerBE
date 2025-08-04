package com.learnerNrunnerBE.learnerNrunnerBE.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements SuccessResponseCode{
    OK(HttpStatus.OK, "200", "Ok"),
    CREATED(HttpStatus.CREATED, "201", "Created");

    private HttpStatus httpStatus;
    private String code;
    private String message;

    @Override
    public ApiResponse<Void> getSuccessResponse() {
        return ApiResponse.onSuccess(this);
    }

    @Override
    public <T> ApiResponse<T> getSuccessResponse(T result){
        return ApiResponse.onSuccess(this, result);
    }

}
