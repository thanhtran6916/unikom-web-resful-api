package com.example.unikomwebresfulapi.exception;

public class NotSaveException extends RuntimeException {

    public static final String SAVE_PERSIST = "Data save failed error";

    public NotSaveException(String message) {
        super(message);
    }
}
