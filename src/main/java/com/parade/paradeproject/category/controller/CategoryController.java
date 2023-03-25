package com.parade.paradeproject.category.controller;

import com.parade.paradeproject.category.dto.DtoOfCategory;
import com.parade.paradeproject.category.service.CategoryService;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    
    @GetMapping("/find/slide")
    @Operation(summary = "取得指定category_id的category其下所關聯之slide資料")
    private List<DataSendModel>
    getAllSlideByCategory(@RequestParam("category_id") Long categoryId) {
        
        return categoryService.getAllSlideByCategory(categoryId);

    }

    @GetMapping("/find/note")
    @Operation(summary = "取得指定category_id的category其下所關聯之note資料")
    private List<DataSendModel>
    getAllNoteByCategory(@RequestParam("category_id") Long categoryId) {

        return categoryService.getAllNoteByCategory(categoryId);
    }


    @GetMapping("/")
    @Operation(summary = "取得使用者所有category資料")
    private List<DataSendModel> getAll() {

        return categoryService.getAll();
    }


    @PutMapping("/")
    @Operation(summary = "沒有帶category_id視為新增資料")
    public boolean addCategory(@RequestBody DtoOfCategory dtoOfCategory) {
        
        return categoryService.save(0L, dtoOfCategory);
    }


    @PutMapping("/{category_id}")
    @Operation(summary = "帶category_id視為修改categoryId資料")
    public boolean updateCategory(@PathVariable(value = "category_id") Long categoryId,
                                  @RequestBody DtoOfCategory dtoOfCategory) {
        
        return categoryService.save(categoryId, dtoOfCategory);
    }


    @PatchMapping("/{category_id}")
    @Operation(summary = "修改成visible狀態",
               description = "visible='N'表示關閉，visible='Y'表示開啟")
    public boolean deleteCategory(@PathVariable(value = "category_id")
                                  Long category_id,
                                  @Pattern(regexp = "[NY]")@RequestParam(name = "is_visible")
                                  String isVisible) {
        
        return categoryService.changeVisible(category_id, isVisible);
    }
    
}
