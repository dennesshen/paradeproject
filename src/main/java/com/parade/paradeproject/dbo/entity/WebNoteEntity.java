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

import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WEBNOTEENTITY")
public class WebNoteEntity {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DtoPresentField
	private Long id; 
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@DtoPresentField
	private String text;
	
	@Column(name="user_id", insertable = false, updatable = false)
	@DtoPresentField
	private Long userId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserAccountEntity userAccountEntity;
	
}
