package com.endava.rest.exception;

import org.springframework.http.HttpStatus;

public class EmployeeErrorInfo {
    private String message;
    private Throwable throwable;
    private HttpStatus httpStatus;

    public EmployeeErrorInfo() {

    }

    public EmployeeErrorInfo(String message,
                             Throwable throwable,
                             HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
