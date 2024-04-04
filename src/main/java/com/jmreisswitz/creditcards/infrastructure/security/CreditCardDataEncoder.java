package com.jmreisswitz.creditcards.infrastructure.security;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;

public interface CreditCardDataEncoder {
    CreditCardData encode(CreditCardData creditCard);
    CreditCardNumber encode(CreditCardNumber creditCardNumber);
}
