package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.JpaAdaptedUserRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.JpaAdapterCreditCardBatchRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.JpaAdapterCreditCardRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository.JpaCreditCardBatchRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository.JpaCreditCardRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfiguration {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaCreditCardRepository jpaCreditCardRepository;

    @Autowired
    private JpaCreditCardBatchRepository jpaCreditCardBatchRepository;

    @Bean
    public UserRepository userRepository() {
        return new JpaAdaptedUserRepository(this.jpaUserRepository);
    }

    @Bean
    public CreditCardRepository creditCardRepository() {
        return new JpaAdapterCreditCardRepository(jpaCreditCardRepository);
    }

    @Bean
    public CreditCardBatchRepository creditCardBatchRepository() {
        return new JpaAdapterCreditCardBatchRepository(jpaCreditCardBatchRepository);
    }
}
