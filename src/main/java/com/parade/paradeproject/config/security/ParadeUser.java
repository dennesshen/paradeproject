package com.parade.paradeproject.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author christinehsieh on 2023/5/3
 */
public class ParadeUser extends User{

    private Long userId;

    public ParadeUser(String username,
                      String password,
                      boolean enabled,
                      boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities,
                      Long userId) {
        super(username,
              password,
              enabled,
              accountNonExpired,
              credentialsNonExpired,
              accountNonLocked,
              authorities);

        this.userId = userId;
    }

    public ParadeUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities,
                      Long userId) {

        super(username, password, authorities);

        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
