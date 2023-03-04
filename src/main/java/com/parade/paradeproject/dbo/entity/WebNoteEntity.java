package com.parade.paradeproject.dbo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parade.paradeproject.util.dataSendModel.DtoPresentField;
import com.parade.paradeproject.util.dataSendModel.DtoPresentNextLevelData;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "WEBNOTEENTITY")
@ToString
public class WebNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DtoPresentField(group = {"aaaa", "", "b"})
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @DtoPresentField(group = {"aaaa", "", "b"})
    private String text;

    @Column(name="user_id", insertable = false, updatable = false)
    @DtoPresentField
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @DtoPresentNextLevelData(name= "user", group = {"b"})
    private UserAccountEntity userAccountEntity;
    
    

}
