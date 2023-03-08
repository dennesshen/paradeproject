package com.parade.paradeproject.webnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parade.paradeproject.dbo.entity.UserAccountEntity;
import com.parade.paradeproject.dbo.entity.WebNoteEntity;
import com.parade.paradeproject.dbo.repository.UserAccountRepository;
import com.parade.paradeproject.dbo.repository.WebNoteRepository;
import com.parade.paradeproject.util.EntityBuilder;
import com.parade.paradeproject.webnote.dto.DtoOfWebNote;

@Service
public class WebNoteService {

	@Autowired
	private UserAccountRepository userRepository;

	@Autowired
	private WebNoteRepository noteRepository;

	@Transactional
	public boolean addNote(DtoOfWebNote recieveData) {

		UserAccountEntity user = userRepository.findById(recieveData.getUserId()).get();

		EntityBuilder<WebNoteEntity> noteEntityBuilder = new EntityBuilder<>();

		WebNoteEntity webNoteEntity =
		noteEntityBuilder.init(new WebNoteEntity())
						 .convertAllDtoToEntity(recieveData)
						 .injectFieldToEntity("userAccountEntity", user)
						 .build();

		noteRepository.saveAndFlush(webNoteEntity);


		return true;
	}
}
