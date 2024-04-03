package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;

public class ExpireDateValidator implements CreditCardDataValidator {
    @Override
    public void validate(CreditCardData creditCard) {
        if (creditCard.expireDate().year() < 2021) {
            throw new IllegalArgumentException("Year must be greater than 2021");
        }
        if (creditCard.expireDate().month() < 1 || creditCard.expireDate().month() > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
    }
}
