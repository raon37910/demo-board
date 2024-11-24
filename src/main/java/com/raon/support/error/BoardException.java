package com.raon.support.error;

import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {

    private final ErrorType errorType;

    private final Object data;

    public BoardException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = null;
    }

    public BoardException(ErrorType errorType, Object data) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = data;
    }
}
