package com.parade.paradeproject.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.parade.paradeproject.dao.entity.base.EntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "USERACCOUNT")
@ToString(exclude = "webNoteEntity")
public class UserAccountEntity extends EntityBase{
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Column
    private String userName;
    
    @Column
    private String password;
    
    @Column
    private String authentication;
    
    @OneToMany(mappedBy = "userAccountEntity")
    private List<SlideEntity> slideEntities;
    
    @OneToMany(mappedBy = "userAccountEntity")
    private List<CategoryEntity> categoryEntities;
   
}
