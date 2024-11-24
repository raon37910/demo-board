package com.raon.controller;

import com.raon.support.error.BoardException;
import com.raon.support.error.ErrorType;
import com.raon.support.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiControllerAdvice {

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<ApiResponse<?>> handleBoardException(BoardException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error("Board Exception: {}", e.getMessage(), e);
            case WARN -> log.warn("Board Exception: {}", e.getMessage(), e);
            default -> log.info("Board Exception: {}", e.getMessage(), e);
        }

        return new ResponseEntity<>(ApiResponse.error(e.getErrorType(), e.getData()), e.getErrorType().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage(), e);

        return new ResponseEntity<>(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.getStatus());
    }
}
