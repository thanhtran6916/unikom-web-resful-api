package com.example.unikomwebresfulapi.helper;

import com.example.unikomwebresfulapi.model.BodyData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResultResp<T> extends ResponseEntity {

    private ResultResp(HttpStatus status, Object body) {
        super(body, status);
    }

    public static ResultResp success(Object data) {
        BodyData bodyData = new BodyData(HttpStatus.OK.value(), data, HttpStatus.OK.getReasonPhrase());
        return new ResultResp(HttpStatus.OK, bodyData);
    }

    public static ResultResp create(Object data) {
        BodyData bodyData = new BodyData(HttpStatus.CREATED.value(), data, HttpStatus.CREATED.getReasonPhrase());
        return new ResultResp(HttpStatus.CREATED, bodyData);
    }

    public static ResultResp delete() {
        return new ResultResp(HttpStatus.OK);
    }

    public ResultResp(HttpStatus status) {
        super(status);
    }

    public ResultResp(Object body, HttpStatus status) {
        super(body, status);
    }

    public ResultResp(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public ResultResp(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }

    public ResultResp(Object body, MultiValueMap headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
