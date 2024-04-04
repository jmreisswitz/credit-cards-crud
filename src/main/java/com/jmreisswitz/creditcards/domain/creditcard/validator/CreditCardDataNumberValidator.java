package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;

public class CreditCardDataNumberValidator implements CreditCardDataValidator {

    private final CreditCardNumberValidator creditCardNumberValidator;

    public CreditCardDataNumberValidator(CreditCardNumberValidator creditCardNumberValidator) {
        this.creditCardNumberValidator = creditCardNumberValidator;
    }

    @Override
    public void validate(CreditCardData creditCard) {
        creditCardNumberValidator.validate(creditCard.number());
    }
}
