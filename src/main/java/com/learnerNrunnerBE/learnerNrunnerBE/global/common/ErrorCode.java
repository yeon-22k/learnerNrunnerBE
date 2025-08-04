package com.learnerNrunnerBE.learnerNrunnerBE.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorResponseCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Internal server error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "Bad request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "403", "Fobidden"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "404", "Not found");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse(){
        return ApiResponse.onFailure(this);
    }

}
