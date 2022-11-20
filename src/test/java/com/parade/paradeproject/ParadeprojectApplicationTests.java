package com.parade.paradeproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.parade.paradeproject.dbo.entity.UserAccountEntity;
import com.parade.paradeproject.dbo.repository.UserAccountRepository;

@SpringBootTest
class ParadeprojectApplicationTests {
	
	
	@Autowired
	private UserAccountRepository user;

	@Test
	void contextLoads() {
		
		UserAccountEntity userAccountEntity = user.findById(1l).get();
		System.out.println(userAccountEntity);
	}

}
