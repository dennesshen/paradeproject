package com.parade.paradeproject;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.UserAccountRepository;

@SpringBootTest
class ParadeprojectApplicationTests {


    @Autowired
    private UserAccountRepository user;

    @Test
    @Transactional
    void contextLoads() {

        UserAccountEntity userAccountEntity = user.findById(1l).get();
        System.out.println(userAccountEntity);
    }


}
