package com.parade.paradeproject.note.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DtoOfNote {
	
	private String slide_id;
	
	private Long node_id;
	
	private Integer note_type;
	
	private String note_text;
	
	private String color;
	
	private String remark;
	
	private DtoOfRange range;
	
	@JsonIgnore
	private Long userId = 1l;
}
