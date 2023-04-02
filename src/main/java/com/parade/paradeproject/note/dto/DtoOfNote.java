package com.parade.paradeproject.note.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DtoOfNote {
    
    private Integer note_type;
    
    private String note_text;
    
    private String color;
    
    private String remark;

    private Long position_x;

    private Long position_y;
    
    @JsonIgnore
    private Long userId = 1l;
    
    private Long slide_id;
    
    private DtoOfRange range;


}
