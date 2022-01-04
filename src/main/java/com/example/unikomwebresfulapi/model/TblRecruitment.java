package com.example.unikomwebresfulapi.model;

import com.example.unikomwebresfulapi.dto.request.TblRecruitmentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_recruitment")
public class TblRecruitment extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int", length = 11, nullable = false)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "responsibility", columnDefinition = "text")
    private String responsibility;

    @Column(name = "benefit", columnDefinition = "text")
    private String benefit;

    @Column(name = "experience",columnDefinition = "text")
    private String experience;

    @Column(name = "skill",columnDefinition = "text")
    private String skill;

    @Column(name = "attitude",columnDefinition = "text")
    private String attitude;

    @Column(name = "expireDate")
    private LocalDate expireDate;

    @Column(name = "salary")
    private String salary;

    @Column(name = "is_delete")
    private boolean deleted;

    public TblRecruitment(TblRecruitmentRequest tblRecruitmentRequest) {
        BeanUtils.copyProperties(tblRecruitmentRequest, this);
    }
}
