package com.parade.paradeproject.category.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.category.dto.DtoOfCategory;
import com.parade.paradeproject.dao.entity.CategoryEntity;
import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.CategoryRepository;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import com.parade.paradeproject.util.EntityBuilder2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {

	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public Map<String, Object> addCategory(DtoOfCategory recieveData, String title) {
		
		UserAccountEntity user = userRepository.findById(recieveData.getUserId()).get();
		
		title = title == null ? "New Category" : title;
		recieveData.setTitle(title);
		
		CategoryEntity categoryEntity = 
		categoryRepository.findById(recieveData.getCategory_id()).orElse(new CategoryEntity());
		
		categoryEntity = 
		EntityBuilder2.init(categoryEntity)
		              .convertAllDtoToEntity(recieveData)
					  .injectFieldToEntity("userAccountEntity", user)
					  .build();
		
		categoryRepository.saveAndFlush(categoryEntity);
		
		Map<String, Object> result = new HashMap<>();
	    result.put("rtn_code", categoryEntity.getId());
	    result.put("rtn_msg", "成功新增category");
	    
		return result;
	}
	
	@Transactional
	public Map<String, Object> updateCategory(DtoOfCategory recieveData, Long id) {
		
		CategoryEntity categoryEntity =
		categoryRepository.findById(id).orElse(null);
		
		categoryEntity = EntityBuilder2.init(categoryEntity)
				.convertAllDtoToEntity(recieveData)
				.build();
		
		categoryRepository.saveAndFlush(categoryEntity);
		
		Map<String, Object> result = new HashMap<>();
	    result.put("rtn_code", categoryEntity.getId());
	    result.put("rtn_msg", "成功修改category");
		return result;
	}
	
	@Transactional
	public Map<String, Object> deleteCategory(Long id) {
		
		CategoryEntity categoryEntity =
		categoryRepository.findById(id).orElse(null);
		
		categoryEntity.setStatus(false);
		
		Map<String, Object> result = new HashMap<>();
	    result.put("rtn_code", categoryEntity.getId());
	    result.put("rtn_msg", "成功刪除category");
	    
		return result;
	}
}
