package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.EncodedCreditCardRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.EncodedUserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.InMemoryCreditCardRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.InMemoryUserRepository;
import com.jmreisswitz.creditcards.infrastructure.security.CreditCardDataEncoder;
import com.jmreisswitz.creditcards.infrastructure.security.SpringCreditCardDataEncoder;
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

    @Bean
    public CreditCardRepository creditCardRepository() {
        CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository();
        CreditCardDataEncoder encoder = new SpringCreditCardDataEncoder(passwordEncoder);
        return new EncodedCreditCardRepository(creditCardRepository, encoder);
    }
}
