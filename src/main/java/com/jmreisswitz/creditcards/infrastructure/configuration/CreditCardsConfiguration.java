package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.domain.creditcard.validator.*;
import com.jmreisswitz.creditcards.infrastructure.security.CreditCardDataEncoder;
import com.jmreisswitz.creditcards.infrastructure.security.SpringCreditCardDataEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class CreditCardsConfiguration {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CreditCardDataEncoder creditCardDataEncoder() {
        return new SpringCreditCardDataEncoder(passwordEncoder);
    }

    @Bean
    public CreditCardDataValidator creditCardDataValidator() {
        return new ComposedCreditCardDataValidator(List.of(
            new CvvValidator(), new ExpireDateValidator(),
                new CreditCardDataNumberValidator(creditCardNumberValidator())
        ));
    }

    @Bean
    public CreditCardNumberValidator creditCardNumberValidator() {
        return new DefaultCreditCardNumberValidator();
    }

}
