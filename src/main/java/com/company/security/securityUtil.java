package com.company.security;


import com.company.users.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class securityUtil {

    public static User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static UUID getUserId() {
        return ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}
