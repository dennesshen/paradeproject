package com.parade.paradeproject.category.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.category.dto.DtoOfCategory;
import com.parade.paradeproject.dao.entity.CategoryEntity;
import com.parade.paradeproject.dao.entity.NoteEntity;
import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.CategoryRepository;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import com.parade.paradeproject.util.EntityBuilder2;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import com.parade.paradeproject.util.dataSendModel.DataSendModelWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {

	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public List<DataSendModel> getAll() {

        return categoryRepository.findAll()
                              .stream()
                              .map(s -> DataSendModelWrapper.wrapper(s))
                              .collect(Collectors.toList());
    }
	
	@Transactional(readOnly = true)
	public DataSendModel getOne(Long categoryId) {
		
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
														  .get();
		
		return DataSendModelWrapper.wrapper(categoryEntity);
	}
	
	@Transactional
	public boolean save(Long categoryId, DtoOfCategory dtoOfCategory) {
		
		UserAccountEntity user = userRepository.findById(dtoOfCategory.getUserId()).get();
		
		CategoryEntity categoryEntity = 
		categoryRepository.findById(dtoOfCategory.getCategory_id())
						  .orElse(new CategoryEntity());
		
		categoryEntity = 
		EntityBuilder2.init(categoryEntity)
		              .convertAllDtoToEntity(dtoOfCategory)
					  .injectFieldToEntity("userAccountEntity", user)
					  .build();
		
		categoryRepository.saveAndFlush(categoryEntity);
		
		return true;
	}
	
	@Transactional
	public boolean deleteCategory(Long id) {
		
		CategoryEntity categoryEntity =
		categoryRepository.findById(id).orElse(null);
		
		categoryEntity.setStatus(false);
	    
		return true;
	}
}
