package com.parade.paradeproject.webhighlight.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DtoOfHighLight {
    
    private String slideId;
    
    private String content;
    
    private String note;
    
    private String color;
    
    private Double[] xy;
    
    @JsonIgnore
    private Long userId = 1l;
}
