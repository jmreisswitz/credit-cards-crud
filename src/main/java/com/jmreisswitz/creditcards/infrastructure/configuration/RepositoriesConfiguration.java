package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.EncodedUserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RepositoriesConfiguration {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public UserRepository userRepository() {
        UserRepository inMemoryUserRepository = new InMemoryUserRepository();
        return new EncodedUserRepository(inMemoryUserRepository, passwordEncoder);
    }

}
