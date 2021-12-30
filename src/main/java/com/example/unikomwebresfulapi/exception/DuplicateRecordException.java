package com.example.unikomwebresfulapi.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String message) {
        super(message);
    }
}
