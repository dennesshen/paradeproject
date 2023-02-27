package com.parade.paradeproject.slide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.slide.service.HttpConnectService;


@RestController
@RequestMapping("/slide")
public class SlideController {
    
    @Autowired
    private HttpConnectService httpConnectService;
    
    @GetMapping("/html")
    public ResponseEntity<String> getHtml(@RequestParam String httpMethod, 
                                          @RequestParam String url, 
                                          @RequestParam(required = false) String body) {
        
        return httpConnectService.connect(httpMethod, url, body);
        
    }
        
        
    
    
    
}
