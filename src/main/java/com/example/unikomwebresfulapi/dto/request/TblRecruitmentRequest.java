package com.example.unikomwebresfulapi.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TblRecruitmentRequest {

    private Long id;

    private String code;

    private String name;

    private String responsibility;

    private String benefit;

    private String experience;

    private String skill;

    private String attitude;

    private LocalDate expireDate;

    private String salary;
}
