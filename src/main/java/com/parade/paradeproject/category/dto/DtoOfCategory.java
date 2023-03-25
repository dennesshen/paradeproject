package com.parade.paradeproject.category.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DtoOfCategory {

    private Long category_id;
    
    private String title;
    
    private Integer sequence;

    @JsonIgnore
    private Long userId = 1l;
}
