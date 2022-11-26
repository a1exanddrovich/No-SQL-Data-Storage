package com.epam.nosqlmodule.controller;

import com.epam.nosqlmodule.exception.ExceptionResponse;
import com.epam.nosqlmodule.exception.NoSuchEntityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionIssuer {

    private static final int CUSTOM_CODE_MULTIPLIER = 100;

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ExceptionResponse> issueNoSuchEntityException() {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .code(HttpStatus.NOT_FOUND.value() * CUSTOM_CODE_MULTIPLIER)
                .message(NoSuchEntityException.class.getSimpleName())
                .build(),
                HttpStatus.NOT_FOUND);
    }

}
