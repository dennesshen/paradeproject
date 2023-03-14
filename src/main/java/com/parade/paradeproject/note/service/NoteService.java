package com.parade.paradeproject.note.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.dao.entity.NoteEntity;
import com.parade.paradeproject.dao.entity.RangeEntity;
import com.parade.paradeproject.dao.entity.SlideEntity;
import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.NoteRepository;
import com.parade.paradeproject.dao.repository.SlideRepository;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import com.parade.paradeproject.note.dto.DtoOfNote;
import com.parade.paradeproject.util.EntityBuilder2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoteService {

    @Autowired
    private UserAccountRepository userRepository;
    
    @Autowired
    private NoteRepository noteRepository;
    
    @Autowired
    private SlideRepository slideRepository;
    
    @Transactional
    public Map<String, Object> saveNote(DtoOfNote recieveData, Long note_id) {
        
        UserAccountEntity user = userRepository.findById(recieveData.getUserId()).get();
        
        SlideEntity slideEntity = slideRepository.findById(recieveData.getSlide_id())
                                                 .get();
        
        NoteEntity noteEntity = noteRepository.findById(note_id)
                                              .orElse(new NoteEntity());

        noteEntity.setId(note_id);
        noteEntity = EntityBuilder2.init(noteEntity)
                                   .convertAllDtoToEntity(recieveData)
                                   .injectFieldToEntity("userAccountEntity", user)
                                   .injectFieldToEntity("slideEntity", slideEntity)
                                   .build();
                    
        
        RangeEntity rangeEntity =
        EntityBuilder2.init(new RangeEntity())
                          .convertAllDtoToEntity(recieveData.getRange())
                          .build();
        
        noteEntity.setRangeEntity(rangeEntity);
        
        noteRepository.saveAndFlush(noteEntity);
        
        Map<String, Object> result = new HashMap<>();
        result.put("rtn_code", noteEntity.getId());
        result.put("rtn_msg", "成功新增/修改note");
    
        return result;
    }
    
    @Transactional
    public Map<String, Object> deleteNote(Long id) {
        
        noteRepository.deleteById(id);
        
        Map<String, Object> result = new HashMap<>();
        result.put("rtn_code", id);
        result.put("rtn_msg", "成功刪除 note");
        
        return result;
    }
    
}
