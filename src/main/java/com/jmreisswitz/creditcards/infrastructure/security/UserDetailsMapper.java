package com.jmreisswitz.creditcards.infrastructure.security;

import com.jmreisswitz.creditcards.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsMapper {

    private UserDetailsMapper() {
        // static class
    }

    public static UserDetails from(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.id().value().toString())
                .password(user.password().value())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
