package com.parade.paradeproject.dbo.entity;

import javax.persistence.CascadeType;
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

import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "NOTE")
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
}
