package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardDataValidator;

public class SaveCreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardDataValidator creditCardDataValidator;

    public SaveCreditCardService(CreditCardRepository creditCardRepository,
                                 CreditCardDataValidator creditCardDataValidator) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardDataValidator = creditCardDataValidator;
    }

    public CreditCard save(CreditCard creditCard) {
        creditCardDataValidator.validate(creditCard.data());
        return creditCardRepository.save(creditCard);
    }
}
