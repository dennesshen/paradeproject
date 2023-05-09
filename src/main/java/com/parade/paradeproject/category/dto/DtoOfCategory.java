package com.parade.paradeproject.category.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.parade.paradeproject.util.UserUtil;
import lombok.Data;

@Data
public class DtoOfCategory {

    private String title;
    
    private Integer sequence;

    @JsonIgnore
    private Long userId = UserUtil.getUserId();
}
