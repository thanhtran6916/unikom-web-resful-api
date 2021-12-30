package com.example.unikomwebresfulapi.helper;

import com.example.unikomwebresfulapi.exception.ErrorResponse;
import com.example.unikomwebresfulapi.exception.NotFoundException;
import com.example.unikomwebresfulapi.exception.NotSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException(NotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(NotSaveException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ErrorResponse handlerNotSaveException(NotSaveException e) {
        return new ErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(), e.getMessage());
    }
}
