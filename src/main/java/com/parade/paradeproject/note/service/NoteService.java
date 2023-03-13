package com.parade.paradeproject.note.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.parade.paradeproject.dbo.entity.NoteEntity;
import com.parade.paradeproject.dbo.entity.RangeEntity;
import com.parade.paradeproject.dbo.entity.UserAccountEntity;
import com.parade.paradeproject.dbo.repository.NoteRepository;
import com.parade.paradeproject.dbo.repository.UserAccountRepository;
import com.parade.paradeproject.note.dto.DtoOfNote;
import com.parade.paradeproject.util.EntityBuilder;
import com.parade.paradeproject.util.EntityBuilder2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoteService {

	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
//	@Autowired
//	private RangeRepository rangeRepository;
	
	
	@Transactional
	public Map<String, Object> addNote(DtoOfNote recieveData, Long note_id) {
		
		UserAccountEntity user = userRepository.findById(recieveData.getUserId()).get();
		
		note_id = note_id == null ? 0L : note_id;
		NoteEntity noteEntity = noteRepository.findById(note_id).orElse(new NoteEntity());
				
		noteEntity = 
		EntityBuilder2.init(noteEntity)
					  .convertAllDtoToEntity(recieveData)
					  .injectFieldToEntity("userAccountEntity", user)
					  .build();
		
		noteEntity.setId(note_id);
		
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
