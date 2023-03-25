package com.parade.paradeproject.slide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.dao.entity.CategoryEntity;
import com.parade.paradeproject.dao.entity.SlideEntity;
import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.CategoryRepository;
import com.parade.paradeproject.dao.repository.SlideRepository;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import com.parade.paradeproject.slide.dto.DtoOfSlide;
import com.parade.paradeproject.util.EntityBuilder2;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import com.parade.paradeproject.util.dataSendModel.DataSendModelWrapper;


/**
 * @author christinehsieh
 *
 */
@Service
public class SlideService {
    
    @Autowired
    private SlideRepository slideRepository;
    
    @Autowired
    private UserAccountRepository userRepository;

    @Autowired CategoryRepository categoryRepository;
    
    @Transactional(readOnly = true)
    public List<DataSendModel> getAll() {

        return slideRepository.findAll()
                              .stream()
                              .map(s -> DataSendModelWrapper.wrapper(s))
                              .collect(Collectors.toList());
        
        
    }
    
    @Transactional(readOnly = true)
    public DataSendModel getOne(Long slideId) {
        
        SlideEntity slideEntity = slideRepository.findById(slideId)
                                                 .get();
        
        return DataSendModelWrapper.wrapper(slideEntity, "getOne");
    }


    
    @Transactional
    public boolean save(Long slideId, DtoOfSlide dtoOfSlide) {
        
        
        UserAccountEntity user = userRepository.findById(1L).get();
        
        SlideEntity slideEntity = slideRepository.findById(slideId)
                                                 .orElse(new SlideEntity());
        CategoryEntity categoryEntity = categoryRepository.findById(dtoOfSlide.getCategory_id())
                                                          .get();
        
        
        EntityBuilder2.init(slideEntity)
                      .injectFieldToEntity("userAccountEntity", user)
                      .injectFieldToEntity("categoryEntity", categoryEntity)
                      .convertAllDtoToEntity(dtoOfSlide);
        
        slideRepository.saveAndFlush(slideEntity);
        
        return true;
        
    }

    public boolean delete(Long slideId) {
        
        slideRepository.deleteById(slideId);
        
        return true;
    }


    
}
