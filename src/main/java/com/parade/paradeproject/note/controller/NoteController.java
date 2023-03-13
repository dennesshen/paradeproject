package com.parade.paradeproject.note.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parade.paradeproject.note.dto.DtoOfNote;
import com.parade.paradeproject.note.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {
<<<<<<< HEAD

    @Autowired
    private NoteRepository noteRepository;
    
    @Autowired
    private NoteService noteService;
    
    @PutMapping("add")
    public ResponseEntity<Map<String, Object>> 
    addNote(@RequestBody DtoOfNote recieveData) {
        return noteService.addNote(recieveData);
    }
    
    @PostMapping("getnote")
    public List<DataSendModel> getNote() {
        
        return noteRepository.findAll()
                             .stream()
                             .map(n -> DataSendModelWrapper.wrapper(n))
                             .collect(Collectors.toList());
    }
=======
	
    @Autowired
    private NoteService noteService;
    
    @PutMapping("/{note_id}")
    public Map<String, Object> addNote(@PathVariable(value = "note_id", required = false) 
                                                      Long noteId,
                                                      @RequestBody DtoOfNote recieveData) {
        return noteService.addNote(recieveData, noteId);
    }
    
    
    @DeleteMapping("/{note_id}")
    public Map<String, Object> deleteNote(@PathVariable(value = "note_id") Long id) {
        return noteService.deleteNote(id);
    }    
>>>>>>> origin/Category_module
}
