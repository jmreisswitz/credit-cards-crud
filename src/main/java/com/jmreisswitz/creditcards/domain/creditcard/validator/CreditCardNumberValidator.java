package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;

public interface CreditCardNumberValidator {
    void validate(CreditCardNumber creditCardNumber);
}
