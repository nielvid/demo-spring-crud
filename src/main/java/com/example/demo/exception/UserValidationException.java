package com.example.demo.exception;

public class UserValidationException extends Exception {

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }


}
