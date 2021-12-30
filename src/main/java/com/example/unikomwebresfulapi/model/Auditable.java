package com.example.unikomwebresfulapi.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @Column(name = "create_at", updatable = false)
    @CreatedDate
    protected LocalDateTime createAt;

    @CreatedBy
    @Column(name="created_by", updatable = false)
    protected T createdBy;

    @Column(name = "update_at")
    @LastModifiedDate
    protected LocalDateTime updateAt;

    @LastModifiedBy
    @Column(name="update_by")
    protected T updateBy;

}
