package com.parade.paradeproject.dbo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HIGHLIGHTENTITY")
@EqualsAndHashCode
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String Slid_id;
	
	@Column
	private Integer note_type;
	
	@Lob
	private String note_text;
	
	@Column
	private String color;
	
	@Column
	private String remark;
	
	@Column(insertable = false, updatable = false)
	private Long range_id;
	
	@OneToOne
	@JoinColumn(name = "range_id")
	private RangeEntity rangeEntity;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserAccountEntity userAccountEntity;
}
