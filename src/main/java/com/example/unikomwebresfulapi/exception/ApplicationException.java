package com.example.unikomwebresfulapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationException extends  RuntimeException{

    private String errorCode;

}
