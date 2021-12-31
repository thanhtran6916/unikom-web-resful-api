package com.example.unikomwebresfulapi.helper;

import com.example.unikomwebresfulapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException(ApplicationException e) {
        return new ErrorResponse(e);
    }

//    @ExceptionHandler(NotSaveException.class)
//    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
//    public ErrorResponse handlerNotSaveException(NotSaveException e) {
//        return new ErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(), e.getMessage());
//    }
//
//    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handlerDuplicateRecordException(BadRequestException e, WebRequest req) {
//        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ExceptionMessage.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handlerException(Exception e, WebRequest req) {
//        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.INTERNAL_SERVER_ERROR);
//    }
}
