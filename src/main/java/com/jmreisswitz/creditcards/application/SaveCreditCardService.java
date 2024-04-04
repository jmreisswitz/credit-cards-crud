package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.SaveCreditCardDomainService;

public class SaveCreditCardService {

    private final SaveCreditCardDomainService domainService;

    public SaveCreditCardService(SaveCreditCardDomainService domainService) {
        this.domainService = domainService;
    }

    public CreditCard save(CreditCard creditCard) {
        return domainService.save(creditCard);
    }
}
