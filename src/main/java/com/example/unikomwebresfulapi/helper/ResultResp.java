package com.example.unikomwebresfulapi.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResultResp {

    private int status = 1;

    private Object data;

    public ResultResp(Object data) {
        this.data = data;
    }
}
