package com.example.unikomwebresfulapi.model;

import com.example.unikomwebresfulapi.dto.request.BlogRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String headline;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDate date;

    @Column(length = 20)
    private String author;

    @Column( columnDefinition = "integer default 0")
    private int view;

    private boolean isDeleted;

    public Blog(BlogRequest blogRequest) {
        BeanUtils.copyProperties(blogRequest, this);
    }
}

