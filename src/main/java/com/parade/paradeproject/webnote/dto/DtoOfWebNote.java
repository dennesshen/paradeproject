package com.parade.paradeproject.webnote.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DtoOfWebNote {

	private String text;

	@JsonIgnore
	private Long userId = 1l;
}
