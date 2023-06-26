package com.cjss.exceptions;

public class PermissionDeniedToAccessException extends RuntimeException {
    public PermissionDeniedToAccessException(String message) {
        super(message);
    }
}
