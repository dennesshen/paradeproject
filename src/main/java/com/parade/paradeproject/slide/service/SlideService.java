package com.parade.paradeproject.slide.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.dao.entity.SlideEntity;
import com.parade.paradeproject.dao.entity.UserAccountEntity;
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

    
    @Transactional(readOnly = true)
    public List<DataSendModel> getAll() {

        return slideRepository.findAll()
                              .stream()
                              .filter(slide -> slide.getIsVisible())
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
        
        
        
        EntityBuilder2.init(slideEntity)
                      .injectFieldToEntity("userAccountEntity", user)
                      .convertAllDtoToEntity(dtoOfSlide);

        if (slideEntity.getIsVisible()==null) slideEntity.setIsVisible(true);

        slideRepository.saveAndFlush(slideEntity);
        
        return true;
        
    }

    public boolean changeVisible(Long slideId, Boolean isVisible) {
        
        SlideEntity slideEntity = slideRepository.findById(slideId).get();
        slideEntity.setIsVisible(isVisible);
        slideRepository.saveAndFlush(slideEntity);

        return true;

    }


    
}
