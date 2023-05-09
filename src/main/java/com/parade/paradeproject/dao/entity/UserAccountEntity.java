package com.parade.paradeproject.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.parade.paradeproject.dao.entity.base.EntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@Entity
@Table(name = "USERACCOUNT")
@EntityListeners(AuditingEntityListener.class)
public class UserAccountEntity extends EntityBase{
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String password;
    
    @Column
    private String authentication;

    @Column(unique = true)
    private String email;

    @Column
    private Boolean enable;
    
    @OneToMany(mappedBy = "userAccountEntity")
    private List<SlideEntity> slideEntities;
    
    @OneToMany(mappedBy = "userAccountEntity")
    private List<CategoryEntity> categoryEntities;
   
}
