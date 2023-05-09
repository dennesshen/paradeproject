package com.parade.paradeproject.config.security;

import com.parade.paradeproject.dao.entity.UserAccountEntity;
import com.parade.paradeproject.dao.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author christinehsieh on 2023/5/3
 */
@Service
public class ParadeUserService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserAccountEntity user =
        userRepository
            .findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "
                                                            + username));


        return new ParadeUser(user.getUserName(),
                              user.getPassword(),
                              user.getEnable(),
                             true,
                             true,
                             true,
                              AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthentication()),
                              user.getId());
    }
}
