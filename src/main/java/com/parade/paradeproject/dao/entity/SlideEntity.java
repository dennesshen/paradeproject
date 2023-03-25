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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.parade.paradeproject.dao.entity.base.EntityBase;
import com.parade.paradeproject.util.dataSendModel.DtoPresentField;
import com.parade.paradeproject.util.dataSendModel.DtoPresentNextLevelData;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author christinehsieh
 *
 */
@Getter
@Setter
@Entity
@Table(name = "SLIDE")
@EntityListeners(AuditingEntityListener.class)
public class SlideEntity extends EntityBase{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DtoPresentField(group = "getOne")
    private Long id;

    @Column
    @DtoPresentField(group = "getOne")
    private String title;

    @Column(nullable = false)
    private Boolean isVisible;
    
    @Lob
    @DtoPresentField(group = "getOne")
    private String webUrl;
    
    @Lob
    @DtoPresentField(group = "getOne")
    private String pictureUrl;

    @DtoPresentField
    @Column(updatable = false, insertable = false)
    private String category_id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity userAccountEntity;
    
    @OneToMany(mappedBy = "slideEntity", cascade = CascadeType.ALL)
    @DtoPresentNextLevelData(name = "note_data", group = "getOne", includeDefault = false)
    private List<NoteEntity> noteEntities;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;
    
    

}
