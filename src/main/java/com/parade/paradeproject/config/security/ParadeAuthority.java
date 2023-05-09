package com.parade.paradeproject.config.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author christinehsieh on 2023/5/3
 */
public class ParadeAuthority implements GrantedAuthority {

    private String authority;

    public ParadeAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
