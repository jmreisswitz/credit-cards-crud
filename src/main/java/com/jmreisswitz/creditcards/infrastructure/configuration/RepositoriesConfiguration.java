package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.*;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.JpaAdaptedUserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.JpaAdapterCreditCardBatchRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.JpaAdapterCreditCardRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository.JpaCreditCardBatchRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository.JpaCreditCardRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository.JpaUserRepository;
import com.jmreisswitz.creditcards.infrastructure.security.CreditCardDataEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RepositoriesConfiguration {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CreditCardDataEncoder creditCardDataEncoder;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaCreditCardRepository jpaCreditCardRepository;

    @Autowired
    private JpaCreditCardBatchRepository jpaCreditCardBatchRepository;

    @Bean
    public UserRepository userRepository() {
        var jpaAdaptedUserRepository = new JpaAdaptedUserRepository(this.jpaUserRepository);
        return new EncodedUserRepository(jpaAdaptedUserRepository, passwordEncoder);
    }

    @Bean
    public CreditCardRepository creditCardRepository() {
        CreditCardRepository creditCardRepository = new JpaAdapterCreditCardRepository(jpaCreditCardRepository);
        return new EncodedCreditCardRepository(creditCardRepository, creditCardDataEncoder);
    }

    @Bean
    public CreditCardBatchRepository creditCardBatchRepository() {
        var creditCardBatchRepository = new JpaAdapterCreditCardBatchRepository(jpaCreditCardBatchRepository);
        return new EncodedCreditCardBatchRepository(creditCardBatchRepository, creditCardDataEncoder);
    }
}
