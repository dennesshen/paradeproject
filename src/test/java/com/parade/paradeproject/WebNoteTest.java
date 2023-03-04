package com.parade.paradeproject;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parade.paradeproject.dbo.entity.UserAccountEntity;
import com.parade.paradeproject.dbo.entity.WebNoteEntity;
import com.parade.paradeproject.dbo.repository.UserAccountRepository;
import com.parade.paradeproject.dbo.repository.WebNoteRepository;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import com.parade.paradeproject.util.dataSendModel.DataSendModelWrapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class WebNoteTest{


    @Autowired
    private WebNoteRepository noteRepository;
    
    @Autowired
    private UserAccountRepository userRepository; 

//    @BeforeTest
//    public static void insertWebNote() {
//        
//        log.info("--------- start webnote test -------");
//        
//    }
    
    @Test
    @Transactional
    void contextLoads() throws JsonProcessingException {
        
        WebNoteEntity noteEntity = new WebNoteEntity();
        noteEntity.setText("test");
        noteEntity.setUserAccountEntity(userRepository.findById(1L).get());
        
        noteRepository.saveAndFlush(noteEntity);
        
        WebNoteEntity webnote = noteRepository.findAll().get(0);
        
        log.info(webnote.toString());
        
        DataSendModel model1 = DataSendModelWrapper.wrapper(webnote);
        System.out.println(model1);
        
        DataSendModel model2 = DataSendModelWrapper.wrapper(webnote, "aaaa");
        System.out.println(model2);
        
        DataSendModel model3 = DataSendModelWrapper.wrapper(webnote, "b");
        System.out.println(model3);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(model3);
        System.out.println(jsonString);
        
    }


}
