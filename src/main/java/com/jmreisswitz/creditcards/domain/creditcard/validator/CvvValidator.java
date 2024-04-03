package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;

public class CvvValidator implements CreditCardDataValidator {
    @Override
    public void validate(CreditCardData creditCard) {
        if (creditCard.cvv().value().length() != 3) {
            throw new IllegalArgumentException("CVV must have 3 digits");
        }
    }
}
