package com.example.unikomwebresfulapi.exception;

public class NotFoundException extends RuntimeException {

    public static final String ID_NOT_FOUND = "Id do not exist";

    public NotFoundException(String message) {
        super(message);
    }
}
