package com.parade.paradeproject.dao.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.parade.paradeproject.dao.entity.base.EntityBase;
import com.parade.paradeproject.util.dataSendModel.DtoPresentField;
import com.parade.paradeproject.util.dataSendModel.DtoPresentNextLevelData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "NOTE")
@EntityListeners(value = AuditingEntityListener.class)
public class NoteEntity extends EntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DtoPresentField(group = "getOne")
    private Long id;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Integer note_type;
    
    @Lob
    @Column
    @DtoPresentField(group = "getOne")
    private String note_text;
    
    @Column
    @DtoPresentField(group = "getOne")
    private String color;
    
    @Column
    @DtoPresentField
    private String remark;

    @Column
    @DtoPresentField
    private Long position_x;

    @Column
    @DtoPresentField
    private Long position_y;
    
    @DtoPresentField
    @Column(insertable = false, updatable = false)
    private Long range_id;
    
    @DtoPresentField
    @Column(updatable = false, insertable = false)
    private Long slide_id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "range_id")
    @DtoPresentNextLevelData(group = "getOne", name = "range")
    private RangeEntity rangeEntity;
    
    @ManyToOne
    @JoinColumn(name = "slide_id")
    private SlideEntity slideEntity;
    
}
