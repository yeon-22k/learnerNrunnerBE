package com.learnerNrunnerBE.learnerNrunnerBE.global.common;

public interface SuccessResponseCode extends BaseResponseCode{
    ApiResponse<Void> getSuccessResponse();
    <T> ApiResponse<T> getSuccessResponse(T result);
}
