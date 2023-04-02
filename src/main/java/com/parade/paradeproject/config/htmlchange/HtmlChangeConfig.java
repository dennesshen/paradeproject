package com.parade.paradeproject.config.htmlchange;


import com.parade.paradeproject.html.HtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class HtmlChangeConfig {


    @Bean
    public HtmlRegexList htmlChangeRegexList() {
        return new HtmlRegexList(new HashMap<>());
    }




}

