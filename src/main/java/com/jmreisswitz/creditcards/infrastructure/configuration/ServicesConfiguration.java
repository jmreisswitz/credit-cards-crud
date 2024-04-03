package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.application.RegisterService;
import com.jmreisswitz.creditcards.application.SaveCreditCardService;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Bean
    public RegisterService registerService() {
        return new RegisterService(userRepository);
    }

    @Bean
    public SaveCreditCardService saveCreditCardService() {
        return new SaveCreditCardService(creditCardRepository);
    }

}
