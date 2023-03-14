package com.parade.paradeproject.dao.entity.base;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class EntityBase {
    
    @Column
    @CreatedDate
    private LocalDateTime createTime;
    
    @Column
    @LastModifiedDate
    private LocalDateTime updateTime;
}
