package com.parade.paradeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ParadeprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParadeprojectApplication.class, args);
	}

}
