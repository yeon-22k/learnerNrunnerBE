package com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception;

import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ApiResponse;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorCode;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorResponseCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Internal server error"),
    DATA_INTEGRITY_VIOLATION(HttpStatus.CONFLICT, "409", "Data integrity violation"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "400", "Validation error");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse(){
        return ApiResponse.onFailure(this);
    }
}
