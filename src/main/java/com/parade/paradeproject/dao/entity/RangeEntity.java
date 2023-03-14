package com.parade.paradeproject.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.parade.paradeproject.dao.entity.base.EntityBase;
import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RANGE")
@EntityListeners(AuditingEntityListener.class)
public class RangeEntity extends EntityBase{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DtoPresentField
    private Long id;
    
    @Column 
    @DtoPresentField(group = "getOne")
    private String anchor_node_parent_identifier;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Long anchor_node_parent_index;
    
    @Column
    @DtoPresentField(group = "getOne")
    private String anchor_node_identifier;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Integer anchor_node_type;
    
    @Column 
    @DtoPresentField(group = "getOne")
    private Long anchor_node_index;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Integer anchor_node_offset;
    
    @Column
    @DtoPresentField(group = "getOne")
    private String focus_node_parent_identifier;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Long focus_node_parent_index;

    @Column
    @DtoPresentField(group = "getOne")
    private String focus_node_identifier;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Integer focus_node_type;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Long focus_node_index;
    
    @Column
    @DtoPresentField(group = "getOne")
    private Integer focus_node_offset;
    
    @OneToOne(mappedBy = "rangeEntity")
    private NoteEntity noteEntities;
}
