package com.parade.paradeproject.slide.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DtoOfSlide {
    
    @NotBlank
    private String webUrl;


    @Size(max = 255)
    private String title;

    private String pictureUrl;



}
