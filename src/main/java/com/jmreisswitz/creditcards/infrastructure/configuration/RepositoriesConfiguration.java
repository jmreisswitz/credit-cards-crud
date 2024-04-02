package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.domain.UserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

}
