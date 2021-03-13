package com.app.conation.advice.exceptions;

public class NotExistUserException extends RuntimeException {

    public NotExistUserException() {
        super();
    }

    public NotExistUserException(String message) {
        super(message);
    }

    public NotExistUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
