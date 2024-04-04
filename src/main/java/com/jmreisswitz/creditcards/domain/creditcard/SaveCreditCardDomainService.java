package com.jmreisswitz.creditcards.domain.creditcard;

import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardDataValidator;

public class SaveCreditCardDomainService {
    private final CreditCardRepository creditCardRepository;
    private final CreditCardDataValidator creditCardDataValidator;

    public SaveCreditCardDomainService(CreditCardRepository creditCardRepository,
                                       CreditCardDataValidator creditCardDataValidator) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardDataValidator = creditCardDataValidator;
    }

    public CreditCard save(CreditCard creditCard) {
        creditCardDataValidator.validate(creditCard.data());
        return creditCardRepository.save(creditCard);
    }
}
