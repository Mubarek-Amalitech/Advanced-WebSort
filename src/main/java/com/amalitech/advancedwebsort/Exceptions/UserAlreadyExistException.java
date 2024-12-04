package com.amalitech.advancedwebsort.Exceptions;

public class UserAlreadyExistException extends  RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
