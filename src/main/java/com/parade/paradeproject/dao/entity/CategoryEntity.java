package com.parade.paradeproject.dao.entity;

import com.parade.paradeproject.dao.entity.base.EntityBase;
import com.parade.paradeproject.util.dataSendModel.DtoPresentField;
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
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

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
    
    @Column(nullable = false)
    private Boolean isVisible;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity userAccountEntity;
    
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<SlideEntity> slideEntities;
    
}
