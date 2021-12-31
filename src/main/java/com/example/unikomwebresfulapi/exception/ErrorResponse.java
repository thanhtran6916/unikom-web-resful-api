package com.example.unikomwebresfulapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private String errorCode;

    private String message;

    private int status = 1;

    public ErrorResponse(ApplicationException e){
        this.errorCode =e.getErrorCode();
//        this.message = MessageUtils.getMessage(this.errorCode);
    }
}
