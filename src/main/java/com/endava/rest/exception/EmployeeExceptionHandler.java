package com.endava.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(value = {ExmployeeResourceException.class})
    public ResponseEntity<Object> handleApiRequestException(ExmployeeResourceException e) {
        EmployeeErrorInfo apiException = new EmployeeErrorInfo(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
