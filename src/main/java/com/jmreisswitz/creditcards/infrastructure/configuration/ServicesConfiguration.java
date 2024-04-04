package com.jmreisswitz.creditcards.infrastructure.configuration;

import com.jmreisswitz.creditcards.application.*;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.creditcard.SaveCreditCardDomainService;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardDataValidator;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardNumberValidator;
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

    @Autowired
    private CreditCardDataValidator creditCardDataValidator;

    @Autowired
    private CreditCardBatchRepository creditCardBatchRepository;

    @Autowired
    private CreditCardNumberValidator creditCardNumberValidator;

    @Bean
    public SaveCreditCardDomainService saveCreditCardDomainService() {
        return new SaveCreditCardDomainService(creditCardRepository, creditCardDataValidator);
    }

    @Bean
    public RegisterService registerService() {
        return new RegisterService(userRepository);
    }

    @Bean
    public SaveCreditCardService saveCreditCardService() {
        return new SaveCreditCardService(saveCreditCardDomainService());
    }

    @Bean
    public RetrieveCreditCardService retrieveCreditCardService() {
        return new RetrieveCreditCardService(creditCardRepository);
    }

    @Bean
    public SaveCreditCardBatchService saveCreditCardBatchService() {
        return new SaveCreditCardBatchService(creditCardBatchRepository, creditCardNumberValidator);
    }

    @Bean
    public ProcessBatchesService processBatchesService() {
        return new ProcessBatchesService(creditCardBatchRepository, saveCreditCardDomainService());
    }

}
