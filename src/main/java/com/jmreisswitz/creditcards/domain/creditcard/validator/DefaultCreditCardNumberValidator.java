package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import com.jmreisswitz.creditcards.domain.creditcard.InvalidCreditCardDataException;

public class DefaultCreditCardNumberValidator implements CreditCardNumberValidator {
    @Override
    public void validate(CreditCardNumber creditCardNumber) {
        if (creditCardNumber.value().length() != 16) {
            throw new InvalidCreditCardDataException("Credit card number must have 16 digits");
        }
    }
}
