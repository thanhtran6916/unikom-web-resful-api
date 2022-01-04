package com.example.unikomwebresfulapi.dto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class BlogRequest {

    private Long id;

    private String headline;

    private String content;

    private LocalDate date;

    private String author;

    private int view;
}
