package com.parade.paradeproject.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.parade.paradeproject.dao.entity.base.EntityBase;
import com.parade.paradeproject.util.dataSendModel.DtoPresentField;
import com.parade.paradeproject.util.dataSendModel.DtoPresentNextLevelData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="CATEGORY")
@EntityListeners(value = AuditingEntityListener.class)
public class CategoryEntity extends EntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DtoPresentField
    private Long id;
        
    @Column
    @DtoPresentField
    private String title;
    
    @Column
    @DtoPresentField
    private Integer sequence;
    
    @Column
    private Boolean isVisible;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity userAccountEntity;
    
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<SlideEntity> slideEntities;
    
}
