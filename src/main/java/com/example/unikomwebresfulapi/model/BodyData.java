package com.example.unikomwebresfulapi.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BodyData<T> {

    private int httpStatusCode;

    private T data;

    private String reasonPhrase;
}
