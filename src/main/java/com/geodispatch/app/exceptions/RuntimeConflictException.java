package com.geodispatch.app.exceptions;

public class RuntimeConflictException extends RuntimeException {

    public RuntimeConflictException() {
        super();
    }

    public RuntimeConflictException(String message) {
        super(message);
    }

    public RuntimeConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeConflictException(Throwable cause) {
        super(cause);
    }
}