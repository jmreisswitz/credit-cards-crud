package com.jmreisswitz.creditcards.infrastructure.security;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardCvv;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardData;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SpringCreditCardDataEncoder implements CreditCardDataEncoder {

    private final PasswordEncoder passwordEncoder;

    public SpringCreditCardDataEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreditCardData encode(CreditCardData creditCard) {
        return new CreditCardData(
                encode(creditCard.number()),
                encodeCvv(creditCard),
                creditCard.expireDate(),
                creditCard.lastFourDigits(),
                creditCard.holder()
        );
    }

    private CreditCardCvv encodeCvv(CreditCardData creditCard) {
        if (creditCard.cvv() == null) {
            return null;
        }
        return new CreditCardCvv(passwordEncoder.encode(creditCard.cvv().value()));
    }

    @Override
    public CreditCardNumber encode(CreditCardNumber creditCardNumber) {
        return new CreditCardNumber(passwordEncoder.encode(creditCardNumber.value()));
    }
}
