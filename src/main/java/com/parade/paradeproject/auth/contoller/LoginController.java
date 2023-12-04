package com.parade.paradeproject.auth.contoller;

import com.parade.paradeproject.auth.dto.DtoOfLogin;
import com.parade.paradeproject.config.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author christinehsieh on 2023/5/3
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;



    @PostMapping("/login")
    public String login(@Valid@RequestBody DtoOfLogin dtoOfLogin) {

        Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dtoOfLogin.getUsername(),
                                                    dtoOfLogin.getPassword()));

        String jwt = jwtToken.generateToken(authentication);

        return jwt;

    }

    @GetMapping("/test")
    public String test() throws InterruptedException {
        Thread.sleep(2000);
        return "test" + new Date();
    }




}
