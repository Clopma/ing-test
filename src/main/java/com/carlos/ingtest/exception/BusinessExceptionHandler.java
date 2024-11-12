package com.carlos.ingtest.exception;

import org.openapitools.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneOffset;
import java.util.Date;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<ErrorDto> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(new ErrorDto()
                .errorMessage(ex.getMessage())
                .timestamp(new Date().toInstant().atOffset(ZoneOffset.UTC))
                , HttpStatus.UNPROCESSABLE_ENTITY);
    }



}
