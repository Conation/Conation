package com.app.conation.advice.exceptions;

public class AlreadyExistIdException extends RuntimeException {

    public AlreadyExistIdException() {
        super();
    }

    public AlreadyExistIdException(String message) {
        super(message);
    }

    public AlreadyExistIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
