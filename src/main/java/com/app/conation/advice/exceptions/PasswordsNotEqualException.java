package com.app.conation.advice.exceptions;

public class PasswordsNotEqualException extends RuntimeException {

    public PasswordsNotEqualException() {
        super();
    }

    public PasswordsNotEqualException(String message) {
        super(message);
    }

    public PasswordsNotEqualException(String message, Throwable cause) {
        super(message, cause);
    }
}
