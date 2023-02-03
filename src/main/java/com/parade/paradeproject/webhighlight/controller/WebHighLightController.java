package com.parade.paradeproject.webhighlight.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.dbo.repository.WebHighLightRepository;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import com.parade.paradeproject.util.dataSendModel.DataSendModelWrapper;
import com.parade.paradeproject.webhighlight.dto.DtoOfHighLight;
import com.parade.paradeproject.webhighlight.service.WebHighLightService;

@RestController
@RequestMapping("/webhighlight")
public class WebHighLightController {

	@Autowired
	private WebHighLightRepository highLightRepository;

	@Autowired
	private WebHighLightService highLightService;
	
	@PutMapping("/createhighlighter")
	public boolean createHighLighter(DtoOfHighLight recieveData) {
		return highLightService.createHighLighter(recieveData);
	}
	
	@PostMapping("/gethighlighter")
	public List<DataSendModel> getHighLigh() {
		return highLightRepository.findAll().stream()
				   .map(n -> DataSendModelWrapper.wrapper(n))
				   .collect(Collectors.toList());

	}
}
