package com.parade.paradeproject.webnote.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.dbo.repository.WebNoteRepository;
import com.parade.paradeproject.util.dataSendModel.DataSendModel;
import com.parade.paradeproject.util.dataSendModel.DataSendModelWrapper;
import com.parade.paradeproject.webnote.dto.DtoOfNote;
import com.parade.paradeproject.webnote.service.WebNoteService;

@RestController
@RequestMapping("/webnote")
public class WebNoteController {

    @Autowired
    private WebNoteRepository noteRepository;

    @Autowired
    private WebNoteService webNoteService;

    @PutMapping("/add")
    public boolean addNote(DtoOfNote recieveData) {
        return webNoteService.addNote(recieveData);
    }


    @PostMapping("/getnote")
    public List<DataSendModel> getNote() {

        return noteRepository.findAll().stream()
                                       .map(n -> DataSendModelWrapper.wrapper(n))
                                       .collect(Collectors.toList());

    }

}
