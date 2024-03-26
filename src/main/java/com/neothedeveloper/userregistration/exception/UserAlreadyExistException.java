package com.neothedeveloper.userregistration.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {

        super(message);
    }
}
