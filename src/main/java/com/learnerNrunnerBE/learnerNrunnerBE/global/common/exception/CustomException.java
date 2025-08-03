package com.learnerNrunnerBE.learnerNrunnerBE.global.common.exception;

import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorCode;
import com.learnerNrunnerBE.learnerNrunnerBE.global.common.ErrorResponseCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorResponseCode errorCode;

    public CustomException(ErrorResponseCode errorCode) {
        this.errorCode = errorCode;
    }

    public static class NotFoundException extends CustomException {
        public NotFoundException(ErrorResponseCode errorCode) {
            super(errorCode);
        }
    }

    public static class ForbiddenException extends CustomException {
        public ForbiddenException(ErrorResponseCode errorCode) {
            super(errorCode);
        }
    }

    public static class BadRequestException extends CustomException {
        public BadRequestException(ErrorResponseCode errorCode) {
            super(errorCode);
        }
    }


}
