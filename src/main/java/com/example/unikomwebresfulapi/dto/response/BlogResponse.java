package com.example.unikomwebresfulapi.dto.response;

import com.example.unikomwebresfulapi.model.Blog;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class BlogResponse {
    private Long id;

    private String headline;

    private String content;

    private LocalDate date;

    private String author;

    private int view;

    public BlogResponse(Blog source) {
        BeanUtils.copyProperties(source,this);
    }
}
