package com.parade.paradeproject.auth.service;

import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import com.parade.paradeproject.auth.dto.DtoOfRegister;
import com.parade.paradeproject.util.EntityBuilder2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author christinehsieh on 2023/5/3
 */
@Service
public class RegisterService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(DtoOfRegister dtoOfRegister) {

        UserAccountEntity user = EntityBuilder2.init(new UserAccountEntity())
                                            .convertAllDtoToEntity(dtoOfRegister)
                                            .build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthentication("ROLE_USER");

        userAccountRepository.save(user);

        return true;
    }

    public boolean update(Long userId) {

        UserAccountEntity user =
        userAccountRepository.findById(userId)
                             .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnable(true);
        userAccountRepository.saveAndFlush(user);

        return true;

    }
}
