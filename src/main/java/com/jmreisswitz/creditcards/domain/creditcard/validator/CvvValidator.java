package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;
import com.jmreisswitz.creditcards.domain.creditcard.InvalidCreditCardDataException;

public class CvvValidator implements CreditCardDataValidator {
    @Override
    public void validate(CreditCardData creditCard) {
        if (creditCard.cvv() == null) {
            return;
        }
        if (creditCard.cvv().value().length() != 3) {
            throw new InvalidCreditCardDataException("CVV must have 3 digits");
        }
    }
}
