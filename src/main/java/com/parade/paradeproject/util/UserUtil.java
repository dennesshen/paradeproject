package com.parade.paradeproject.util;

import com.parade.paradeproject.config.security.ParadeUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author christinehsieh on 2023/5/3
 */

public class UserUtil {

    public static Long getUserId(){

        ParadeUser user =
            (ParadeUser) SecurityContextHolder.getContext()
                                              .getAuthentication()
                                              .getPrincipal();

        return user.getUserId();

    }



}
