package com.ps.exception;

/**
 * Created by iuliana.cosmina on 6/5/16.
 */
public class UnexpectedException extends RuntimeException {

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
