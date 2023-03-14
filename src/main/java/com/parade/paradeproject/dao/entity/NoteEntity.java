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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "NOTE")
@EntityListeners(value = AuditingEntityListener.class)
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DtoPresentField
	private Long id;
	
	@Column
	@DtoPresentField
	private String slide_id;
	
	@Column
	@DtoPresentField
	private Integer note_type;
	
	@Lob
	@DtoPresentField
	private String note_text;
	
	@Column
	@DtoPresentField
	private String color;
	
	@Column
	@DtoPresentField
	private String remark;
	
	@Column(insertable = false, updatable = false)
	private Long range_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "range_id")
	private RangeEntity rangeEntity;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserAccountEntity userAccountEntity;
	
	@Column
	@CreatedDate
	private LocalDateTime CreateTime;
	
	@Column
	@LastModifiedDate
	private LocalDateTime UpdateTime;
}
