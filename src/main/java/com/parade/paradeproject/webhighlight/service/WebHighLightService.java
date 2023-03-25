package com.parade.paradeproject.webhighlight.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.entity.WebHighLightEntity;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import com.parade.paradeproject.dao.repository.WebHighLightRepository;
import com.parade.paradeproject.util.EntityBuilder;
import com.parade.paradeproject.webhighlight.dto.DtoOfHighLight;

@Service
public class WebHighLightService {

    @Autowired
    private UserAccountRepository userRepository;
    
    @Autowired
    private WebHighLightRepository highLightRepository;
    
    @Transactional
    public boolean createHighLighter(DtoOfHighLight recieveData) {
        
        UserAccountEntity user = userRepository.findById(recieveData.getUserId()).get();
        
        EntityBuilder<WebHighLightEntity> highLightBuilder = new EntityBuilder<>();
        
        WebHighLightEntity webHighLightEntity =
        highLightBuilder.init(new WebHighLightEntity())
                        .convertAllDtoToEntity(recieveData)
                        .injectFieldToEntity("userAccountEntity", user)
                        .build();
        
        String coordinates = Arrays.toString(recieveData.getXy());
        webHighLightEntity.setCoordinates(coordinates);
        
        highLightRepository.saveAndFlush(webHighLightEntity);
        
        return true;
    }
}


