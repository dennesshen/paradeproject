package com.parade.paradeproject.note.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.dbo.entity.NoteEntity;
import com.parade.paradeproject.dbo.entity.RangeEntity;
import com.parade.paradeproject.dbo.entity.UserAccountEntity;
import com.parade.paradeproject.dbo.repository.NoteRepository;
import com.parade.paradeproject.dbo.repository.RangeRepository;
import com.parade.paradeproject.dbo.repository.UserAccountRepository;
import com.parade.paradeproject.note.dto.DtoOfNote;
import com.parade.paradeproject.util.EntityBuilder;

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
	public ResponseEntity<Map<String, Object>> addNote(DtoOfNote recieveData) {
		UserAccountEntity user = userRepository.findById(recieveData.getUserId()).get();
		
		EntityBuilder<NoteEntity> noteEntityBuilder = new EntityBuilder<>();
		EntityBuilder<RangeEntity> rangeEntityBuilder = new EntityBuilder<>();
		
		NoteEntity noteEntity =
		noteEntityBuilder.init(new NoteEntity())
							 .convertAllDtoToEntity(recieveData)
							 .injectFieldToEntity("userAccountEntity", user)
							 .build();
		
		RangeEntity rangeEntity =
		rangeEntityBuilder.init(new RangeEntity())
						  .convertAllDtoToEntity(recieveData.getRange())
						  .build();
		
		noteEntity.setRangeEntity(rangeEntity);
		
		noteRepository.saveAndFlush(noteEntity);
		
		Map<String, Object> result = new HashMap<>();
	    result.put("rtn_code", noteEntity.getId());
	    result.put("rtn_msg", "成功新增highlight");
	
	    return ResponseEntity.ok(result);
	}
}
