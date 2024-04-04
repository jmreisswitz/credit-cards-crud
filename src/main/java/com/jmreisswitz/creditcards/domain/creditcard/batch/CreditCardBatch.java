package com.jmreisswitz.creditcards.domain.creditcard.batch;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import com.jmreisswitz.creditcards.domain.user.UserId;

import java.time.LocalDate;
import java.util.Collection;

public record CreditCardBatch(
        UserId userId,
        String name,
        LocalDate date,
        Collection<Line> creditCards) {

    public static record Line(String identifier, CreditCardNumber creditCardNumber) {
    }
}
