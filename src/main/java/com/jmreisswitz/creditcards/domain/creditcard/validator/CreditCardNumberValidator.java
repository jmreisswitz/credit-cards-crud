package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;
import com.jmreisswitz.creditcards.domain.creditcard.InvalidCreditCardDataException;

public class CreditCardNumberValidator implements CreditCardDataValidator {

    @Override
    public void validate(CreditCardData creditCard) {
        if (creditCard.number().value().length() != 16) {
            throw new InvalidCreditCardDataException("Credit card number must have 16 digits");
        }
    }
}
