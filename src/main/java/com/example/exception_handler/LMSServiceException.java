package com.example.exception_handler;

import org.springframework.http.HttpStatus;

public class LMSServiceException extends RuntimeException {
    private final HttpStatus code;
    private final String message;

    public LMSServiceException(HttpStatus code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}