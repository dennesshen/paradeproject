package com.parade.paradeproject.slide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.slide.dto.DtoOfHttpRequest;
import com.parade.paradeproject.slide.dto.DtoOfSlide;
import com.parade.paradeproject.slide.service.HttpConnectService;
import com.parade.paradeproject.slide.service.SlideService;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/slide")
public class SlideController {
    
    @Autowired
    private HttpConnectService httpConnectService;
    
    @Autowired
    private SlideService slideService;
    
    @GetMapping("/")
    @Operation(summary = "取得使用者所有slide資料")
    private List<DataSendModel> getAll() {
        return slideService.getAll();
    }
    
    @GetMapping("/{slide_id}")
    @Operation(summary = "取得指定slide_id的資料")
    private DataSendModel getOne(@PathVariable("slide_id") Long slideId) {
        
        return slideService.getOne(slideId);
    
    }
    
    @Operation(summary = "沒有帶slide_id視為新增資料")
    @PutMapping("/")
    private void saveSlide(@RequestBody@Validated DtoOfSlide dtoOfSlide) {
        slideService.save(0L, dtoOfSlide);
    }
    
    @Operation(summary = "帶slide_id視為修改該slideId資料")
    @PutMapping("/{slide_id}")
    private void saveSlide(@PathVariable(value = "slide_id", required = false) Long slideId,
                           @RequestBody@Validated DtoOfSlide dtoOfSlide) {
        slideService.save(slideId, dtoOfSlide);
    }
    
    @DeleteMapping("/{slide_id}")
    @Operation(summary = "刪除指定slide_id的資料，會連帶刪除該slide 所關聯的所有note")
    private boolean deleteSlide(@PathVariable("slide_id") Long slideId) {
        
        return slideService.delete(slideId);
    }

    @PostMapping("/html")
    public ResponseEntity<String> getHtml(@RequestBody DtoOfHttpRequest recieveData) {
        
        return httpConnectService.connect(recieveData);
        
    }
        
        
    
    
    
}
