package com.parade.paradeproject.html;


import com.parade.paradeproject.config.htmlchange.HtmlRegexList;
import com.parade.paradeproject.dao.entity.HtmlEntity;
import com.parade.paradeproject.dao.repository.HtmlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service("HtmlService")
@Slf4j
public class HtmlService {
    
    @Autowired
    private HtmlRepository htmlRepository;

    @Autowired
    private HtmlRegexList htmlRegexList;
    
    
    @PostConstruct
    public void postConstruct() {
        htmlRepository.findAll()
                      .forEach(h -> htmlRegexList.regexList()
                                                 .put(h.getId(),
                                                      h.getRegex()));
        log.info("htmlRegexList: {}", htmlRegexList.regexList().values());
    }

    public Map<Long, String> getAll() {
        return htmlRegexList.regexList();
    }

    public boolean savePattern(Long id, String pattern) {

        HtmlEntity htmlEntity =
            htmlRepository.findById(id).orElse(new HtmlEntity());

        htmlEntity.setRegex(pattern);

        htmlEntity = htmlRepository.save(htmlEntity);

        htmlRegexList.regexList().put(htmlEntity.getId(), pattern);

        return true;
    }


    public boolean deletePattern(Long id) {
        htmlRepository.deleteById(id);
        htmlRegexList.regexList().remove(id);
        return true;
    }
}
