package com.parade.paradeproject.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.category.dto.DtoOfCategory;
import com.parade.paradeproject.dao.entity.CategoryEntity;
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
    public List<DataSendModel> getAllSlideByCategory(Long categoryId) {

        return categoryRepository.findById(categoryId)
                                 .get()
                                 .getSlideEntities()
                                 .stream()
                                 .filter(slide -> slide.getIsVisible())
                                 .map(slide -> DataSendModelWrapper.wrapper(slide))
                                 .collect(Collectors.toList());
    }

    public List<DataSendModel> getAllNoteByCategory(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .get()
                .getSlideEntities()
                .stream()
                .flatMap(slide -> slide.getNoteEntities().stream())
                .map(note -> DataSendModelWrapper.wrapper(note))
                .collect(Collectors.toList());
    }



    @Transactional(readOnly = true)
    public List<DataSendModel> getAll() {

        return categoryRepository.findAll()
                                 .stream()
                                 .map(s -> DataSendModelWrapper.wrapper(s))
                                 .collect(Collectors.toList());
    }



    @Transactional
    public Long save(Long categoryId, DtoOfCategory dtoOfCategory) {
        
        UserAccountEntity user = userRepository.findById(dtoOfCategory.getUserId()).get();
        
        CategoryEntity categoryEntity = 
        categoryRepository.findById(categoryId)
                          .orElse(new CategoryEntity());
        
        categoryEntity = 
        EntityBuilder2.init(categoryEntity)
                      .convertAllDtoToEntity(dtoOfCategory)
                      .injectFieldToEntity("userAccountEntity", user)
                      .build();

        if (categoryEntity.getIsVisible()==null) categoryEntity.setIsVisible(true);
        

        
        return categoryRepository.saveAndFlush(categoryEntity).getId();
    }
    



    @Transactional
    public boolean changeVisible(Long id, Boolean isVisible) {

        CategoryEntity categoryEntity = categoryRepository.findById(id).get();

        categoryEntity.setIsVisible(isVisible);
        categoryRepository.saveAndFlush(categoryEntity);

        return true;
    }



}
