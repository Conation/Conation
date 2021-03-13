package com.app.conation.advice.exceptions;

public class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException() {
        super();
    }

    public RegionNotFoundException(String message) {
        super(message);
    }

    public RegionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
