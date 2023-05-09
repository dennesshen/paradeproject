package com.parade.paradeproject.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author christinehsieh on 2023/5/3
 */
@Data
public class DtoOfLogin {

    @NotBlank
    @Pattern(regexp = "[^'-]*")
    private String username;

    @NotBlank
    private String password;

}
