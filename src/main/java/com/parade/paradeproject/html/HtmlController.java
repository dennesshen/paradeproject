package com.parade.paradeproject.html;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/html")
public class HtmlController {

    @Autowired
    private HtmlService htmlService;

    @GetMapping("/pattern/")
    public Map<Long, String> getAll() {
        return htmlService.getAll();
    }

    @PostMapping("/pattern")
    public boolean addPattern(@NotNull@RequestParam String pattern) {
        return htmlService.savePattern(0L, pattern);
    }

    @PostMapping("/pattern/{id}")
    public boolean addPattern(@PathVariable("id") Long id,
                              @NotNull@RequestParam String pattern) {
        return htmlService.savePattern(id, pattern);
    }

    @DeleteMapping("/pattern/{id}")
    public boolean deletePattern(@PathVariable("id") Long id) {
        return htmlService.deletePattern(id);
    }


}
