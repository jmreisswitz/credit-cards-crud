package com.jmreisswitz.creditcards.domain.creditcard.validator;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;

import java.util.List;

public class ComposedCreditCardDataValidator implements CreditCardDataValidator {

    private final List<CreditCardDataValidator> validators;

    public ComposedCreditCardDataValidator(List<CreditCardDataValidator> validators) {
        this.validators = validators;
    }

    @Override
    public void validate(CreditCardData creditCard) {
        validators.forEach(validator -> validator.validate(creditCard));
    }
}
