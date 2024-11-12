package com.carlos.ingtest.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    HttpStatus httpStatus;

    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
