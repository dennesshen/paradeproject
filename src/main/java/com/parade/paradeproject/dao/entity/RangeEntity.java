package com.parade.paradeproject.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.parade.paradeproject.util.dataSendModel.DtoPresentField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RANGE")
public class RangeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DtoPresentField
	private Long id;
	
	@Column 
	@DtoPresentField
	private String anchor_node_parent_identifier;
	
	@Column
	@DtoPresentField
	private Long anchor_node_parent_index;
	
	@Column
	@DtoPresentField
	private String anchor_node_identifier;
	
	@Column
	@DtoPresentField
	private Integer anchor_node_type;
	
	@Column 
	@DtoPresentField
	private Long anchor_node_index;
	
	@Column
	@DtoPresentField
	private Integer anchor_node_offset;
	
	@Column
	@DtoPresentField
	private String focus_node_parent_identifier;
	
	@Column
	@DtoPresentField
	private Long focus_node_parent_index;
	
	@Column
	@DtoPresentField
	private String focus_node_identifier;
	
	@Column
	@DtoPresentField
	private Integer focus_node_type;
	
	@Column
	@DtoPresentField
	private Long focus_node_index;
	
	@Column
	@DtoPresentField
	private Integer focus_node_offset;
	
	@OneToOne(mappedBy = "rangeEntity")
	private NoteEntity noteEntities;
}
