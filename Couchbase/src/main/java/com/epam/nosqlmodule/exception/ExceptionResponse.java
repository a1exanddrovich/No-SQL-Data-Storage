package com.epam.nosqlmodule.exception;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExceptionResponse {

    private final int code;
    private final String message;

}
