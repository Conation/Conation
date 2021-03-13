package com.app.conation.advice.exceptions;

public class JWTDecodeException extends RuntimeException {

    public JWTDecodeException() {
        super();
    }

    public JWTDecodeException(String message) {
        super(message);
    }

    public JWTDecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
