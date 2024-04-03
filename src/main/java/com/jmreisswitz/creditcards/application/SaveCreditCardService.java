package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;

public class SaveCreditCardService {

    private final CreditCardRepository creditCardRepository;

    public SaveCreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public void save(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }
}
