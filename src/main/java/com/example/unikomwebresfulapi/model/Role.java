package com.example.unikomwebresfulapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11)
    private Long id;

    @Column(nullable = false)
    private String name;
}

