package com.jmreisswitz.creditcards.infrastructure.security;


import com.jmreisswitz.creditcards.domain.user.UserId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public SecurityFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            tryToAuthenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }

    private void tryToAuthenticate(String token) {
        String userIdAsString = jwtUtils.validateToken(token);
        if (userIdAsString.isEmpty()) {
            return;
        }
        UserId userId = new UserId(Long.parseLong(userIdAsString));
        authenticate(userId);
    }

    private static void authenticate(UserId userId) {
        var authentication = new UsernamePasswordAuthenticationToken(userId, null,
                AuthorityUtils.createAuthorityList("USER"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
