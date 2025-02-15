package com.jmreisswitz.creditcards.infrastructure.controller;

import com.jmreisswitz.creditcards.domain.user.UserId;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

public class PrincipalMapper {

    private PrincipalMapper() {
        // static class
    }

    public static UserId userIdFrom(Principal principal) {
        return (UserId) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

}
