package com.parade.paradeproject.slide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.slide.dto.DtoOfHttpRequest;
import com.parade.paradeproject.slide.service.HttpConnectService;


@RestController
@RequestMapping("/slide")
public class SlideController {
    
    @Autowired
    private HttpConnectService httpConnectService;
    
    @PostMapping("/html")
    public ResponseEntity<String> getHtml(@RequestBody DtoOfHttpRequest recieveData) {
        
        return httpConnectService.connect(recieveData);
        
    }
        
        
    
    
    
}
