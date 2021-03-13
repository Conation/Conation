package com.app.conation.advice.exceptions;

public class NotEnoughPointException extends RuntimeException {

    public NotEnoughPointException() {
        super();
    }

    public NotEnoughPointException(String message) {
        super(message);
    }

    public NotEnoughPointException(String message, Throwable cause) {
        super(message, cause);
    }
}
