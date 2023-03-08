package com.parade.paradeproject.dbo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RANGE")
public class RangeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column 
	private String anchor_node_parent_identifier;
	
	@Column
	private Long anchor_node_parent_index;
	
	@Column
	private String anchor_node_identifier;
	
	@Column
	private Integer anchor_node_type;
	
	@Column 
	private Long anchor_node_index;
	
	@Column
	private Integer anchor_node_offset;
	
	@Column
	private String focus_node_parent_identifier;
	
	@Column
	private Long focus_node_parent_index;
	
	@Column
	private String focus_node_identifier;
	
	@Column
	private Integer focus_node_type;
	
	@Column
	private Long focus_node_index;
	
	@Column
	private Integer focus_node_offset;
	
	@OneToOne(mappedBy = "rangeEntity")
	private NoteEntity noteEntities;
}
