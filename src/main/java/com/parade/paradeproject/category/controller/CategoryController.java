package com.parade.paradeproject.category.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.category.dto.DtoOfCategory;
import com.parade.paradeproject.category.service.CategoryService;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	@Operation(summary = "取得使用者所有category資料")
	private List<DataSendModel> getAll() {
		
		return categoryService.getAll();
	}
	
	@GetMapping("/{category_id}")
	@Operation(summary = "取得指定category_id的資料")
	private DataSendModel getOne(@PathVariable("category_id") Long categoryId) {
		
		return categoryService.getOne(categoryId);
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
	
	@DeleteMapping("/{category_id}")
	@Operation(summary = "需修改成status 不刪除")
    public boolean deleteCategory(@PathVariable(value = "category_id") Long id) {
        
		return categoryService.deleteCategory(id);
    }
	
}
