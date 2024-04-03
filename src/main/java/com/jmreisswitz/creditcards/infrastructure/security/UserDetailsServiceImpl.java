package com.jmreisswitz.creditcards.infrastructure.security;

import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.domain.user.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findBy(new Username(username));
        return UserDetailsMapper.from(user);
    }
}
