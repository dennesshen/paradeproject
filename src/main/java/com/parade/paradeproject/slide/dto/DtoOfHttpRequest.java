package com.parade.paradeproject.slide.dto;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DtoOfHttpRequest {
    
    @NotBlank
    private String httpMethod = "GET";
    
    @NotNull
    private Map<String, String> headers;
     
    @NotBlank
    private String url;
    
    private String body;
    
    
}
