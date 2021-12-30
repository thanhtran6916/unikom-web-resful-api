package com.example.unikomwebresfulapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException (NotFoundException e, WebRequest req) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "User không tồn tại trong hệ thống!!!");
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerDuplicateRecordException(DuplicateRecordException e, WebRequest req) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Yêu cầu bị từ chối");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception e, WebRequest req) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi! Vui lòng quay lại trang đăng nhập");
    }
}
