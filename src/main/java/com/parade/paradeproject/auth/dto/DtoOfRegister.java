package com.parade.paradeproject.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * author christinehsieh on 2023/5/3
 */
@Data
public class DtoOfRegister {

    @NotBlank
    @Pattern(regexp = "[^'-]*")
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;


}
