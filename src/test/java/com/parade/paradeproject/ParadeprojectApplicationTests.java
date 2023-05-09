package com.parade.paradeproject;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class ParadeprojectApplicationTests {


    @Autowired
    private UserAccountRepository user;


    @Test
    @Transactional
    @Rollback(false)
    void contextLoads() {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        UserAccountEntity userAccountEntity = user.findById(1l).get();
        userAccountEntity.setPassword(encoder.encode(userAccountEntity.getPassword()));
        userAccountEntity.setEnable(true);
        user.saveAndFlush(userAccountEntity);

    }


}
