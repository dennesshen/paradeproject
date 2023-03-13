package com.parade.paradeproject.category.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.category.dto.DtoOfCategory;
import com.parade.paradeproject.category.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PutMapping("/{title}")
	public Map<String, Object> addCategory(@PathVariable(value = "title", required = false)
	                                                     String title,
	                                                     @RequestBody DtoOfCategory recieveData) {
		return categoryService.addCategory(recieveData, title);
	}
	
	@PutMapping("/update/{category_id}")
	public Map<String, Object> updateCategory(@PathVariable(value = "category_id") 
	                                                        Long id,
	                                                        @RequestBody DtoOfCategory recieveData) {
		
		return categoryService.updateCategory(recieveData, id);
	}
	
	@DeleteMapping("/{category_id}")
    public Map<String, Object> deleteCategory(@PathVariable(value = "category_id") Long id) {
        
		return categoryService.deleteCategory(id);
    }
}
