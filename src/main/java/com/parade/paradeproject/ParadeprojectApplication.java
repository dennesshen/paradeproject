package com.parade.paradeproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class ParadeprojectApplication {



    public static void main(String[] args) {

        SpringApplication.run(ParadeprojectApplication.class, args);


    }

    
}
