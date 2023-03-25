package com.parade.paradeproject.note.controller;

import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
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
    
    @Autowired
    private NoteService noteService;


    @PutMapping("/")
    @Operation(summary = "沒有帶note_id，表示新增資料")
    public Map<String, Object> saveNote(@RequestBody DtoOfNote recieveData) {
        return noteService.saveNote(recieveData, 0L);
    }
    
    @PutMapping("/{note_id}")
    @Operation(summary = "有帶note_id，表示更新該筆note資料")
    public Map<String, Object> saveNote(@PathVariable(value = "note_id", required = false)
                                        Long noteId,
                                        @RequestBody
                                        DtoOfNote recieveData) {

        return noteService.saveNote(recieveData, noteId);

    }
    
    @DeleteMapping("/{note_id}")
    @Operation(summary = "這個API會真實從DB當中刪除該note_id的資料")
    public Map<String, Object> deleteNote(@PathVariable(value = "note_id") Long id) {
        return noteService.deleteNote(id);
    }    

}
