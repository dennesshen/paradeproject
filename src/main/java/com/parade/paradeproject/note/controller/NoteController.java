package com.parade.paradeproject.note.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.dbo.repository.NoteRepository;
import com.parade.paradeproject.note.dto.DtoOfNote;
import com.parade.paradeproject.note.service.NoteService;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import com.parade.paradeproject.util.dataSendModel.DataSendModelWrapper;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private NoteService noteService;
	
	@PutMapping("add")
	public ResponseEntity<Map<String, Object>> addNote(@RequestBody DtoOfNote recieveData) {
		return noteService.addNote(recieveData);
	}
	
	@PostMapping("getnote")
	public List<DataSendModel> getNote() {
		
		return noteRepository.findAll().stream()
											.map(n -> DataSendModelWrapper.wrapper(n))
											.collect(Collectors.toList());
	}
}
