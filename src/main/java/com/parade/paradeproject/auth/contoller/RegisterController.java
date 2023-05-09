package com.parade.paradeproject.auth.contoller;

import com.parade.paradeproject.auth.dto.DtoOfRegister;
import com.parade.paradeproject.auth.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author christinehsieh on 2023/5/3
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;


    @PutMapping("/")
    public boolean register(@Valid@RequestBody DtoOfRegister dtoOfRegister) {

        return registerService.register(dtoOfRegister);
    }

    @PatchMapping("/active/{user_id}")
    public boolean update(@PathVariable(name = "user_id") Long userId) {

        return registerService.update(userId);
    }


}
