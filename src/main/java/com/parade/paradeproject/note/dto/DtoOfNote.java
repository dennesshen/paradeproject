package com.parade.paradeproject.note.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DtoOfNote {
	
	@JsonProperty("slide_id")
	private String slideId;
	
	@JsonProperty("note_type")
	private Integer noteType;
	
	@JsonProperty("note_text")
	private String noteText;
	
	private String color;
	
	private String remark;
	
	private DtoOfRange range;
	
	@JsonIgnore
	private Long userId = 1l;
}
