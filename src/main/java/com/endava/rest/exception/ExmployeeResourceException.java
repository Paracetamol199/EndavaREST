package com.endava.rest.exception;

public class ExmployeeResourceException extends RuntimeException {

    public ExmployeeResourceException(String message) {
        super(message);
    }

    public ExmployeeResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
