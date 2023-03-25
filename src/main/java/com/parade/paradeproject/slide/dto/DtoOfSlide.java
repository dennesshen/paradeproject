package com.parade.paradeproject.slide.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DtoOfSlide {
    
    @NotBlank
    private String webUrl;

    private String pictureUrl;

    private Long category_id;
}
