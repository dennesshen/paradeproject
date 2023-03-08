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

import com.parade.paradeproject.dbo.repository.HighlightRepository;
import com.parade.paradeproject.note.dto.DtoOfHighlight;
import com.parade.paradeproject.note.service.HighlightService;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import com.parade.paradeproject.util.dataSendModel.DataSendModelWrapper;

@RestController
@RequestMapping("/highlight")
public class NoteController {

	@Autowired
	private HighlightRepository highlightRepository;
	
	@Autowired
	private HighlightService highlightService;
	
	@PutMapping("add")
	public ResponseEntity<Map<String, Object>> addHighlight(@RequestBody DtoOfHighlight recieveData) {
		return highlightService.addHightlight(recieveData);
	}
	
	@PostMapping("gethghlight")
	public List<DataSendModel> getlighlight() {
		
		return highlightRepository.findAll().stream()
											.map(n -> DataSendModelWrapper.wrapper(n))
											.collect(Collectors.toList());
	}
}
