package com.parade.paradeproject.slide.controller;

import com.parade.paradeproject.slide.dto.DtoOfHttpRequest;
import com.parade.paradeproject.slide.dto.DtoOfSlide;
import com.parade.paradeproject.slide.service.HttpConnectService;
import com.parade.paradeproject.slide.service.SlideService;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    @Operation(summary = "取得指定slide_id的資料以及其下關聯之note 資料")
    private DataSendModel getOne(@PathVariable("slide_id") Long slideId) {
        
        return slideService.getOne(slideId);
    
    }
    
    @Operation(summary = "沒有帶slide_id視為新增資料")
    @PutMapping("/")
    private boolean saveSlide(@RequestBody@Validated DtoOfSlide dtoOfSlide) {

       return slideService.save(0L, dtoOfSlide);

    }
    
    @Operation(summary = "帶slide_id視為修改該slideId資料")
    @PutMapping("/{slide_id}")
    private boolean saveSlide(@PathVariable(value = "slide_id", required = false) Long slideId,
                              @RequestBody@Validated DtoOfSlide dtoOfSlide) {
        return slideService.save(slideId, dtoOfSlide);
    }
    
    @PatchMapping("/{slide_id}")
    @Operation(summary = "將指定slide_id的資料visible設定成false或true")
    private boolean deleteSlide(@PathVariable("slide_id") Long slideId,
                                @RequestParam("visible")
                                Boolean isVisible) {
        
        return slideService.changeVisible(slideId, isVisible);
    }

    @PostMapping("/html")
    public ResponseEntity<HttpConnectService.HttpRecord>
    getHtml(@RequestBody DtoOfHttpRequest recieveData) {
        
        return httpConnectService.connect(recieveData);
        
    }
        
        
    
    
    
}
