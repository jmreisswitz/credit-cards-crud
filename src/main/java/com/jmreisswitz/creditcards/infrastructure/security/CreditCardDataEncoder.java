package com.jmreisswitz.creditcards.infrastructure.security;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;

public interface CreditCardDataEncoder {
    CreditCardData encode(CreditCardData creditCard);
}
