package com.jmreisswitz.creditcards.domain.creditcard.batch;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import com.jmreisswitz.creditcards.domain.creditcard.InvalidCreditCardDataException;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardNumberValidator;
import com.jmreisswitz.creditcards.domain.user.UserId;

import java.time.LocalDate;
import java.util.Collection;

public class CreditCardBatch {
    private final UserId userId;
    private final String name;
    private final LocalDate date;
    private Collection<Line> creditCards;

    public CreditCardBatch(UserId userId, String name, LocalDate date, Collection<Line> creditCards) {
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.creditCards = creditCards;
    }

    public void validateLines(CreditCardNumberValidator validator) {
        creditCards = creditCards.stream()
                .map(line -> {
                    if (line.isValid(validator)) {
                        return line;
                    }
                    return line.asInvalid();})
                .toList();
    }

    public String name() {
        return name;
    }

    public UserId userId() {
        return userId;
    }

    public LocalDate date() {
        return date;
    }

    public Collection<Line> creditCards() {
        return creditCards;
    }

    public static record Line(String identifier,
                              CreditCardNumber creditCardNumber,
                              CreditCardBatchLineStatus status) {

        public boolean isValid(CreditCardNumberValidator creditCardNumberValidator) {
            try {
                creditCardNumberValidator.validate(creditCardNumber);
                return true;
            } catch (InvalidCreditCardDataException e) {
                return false;
            }
        }

        public Line asInvalid() {
            return new Line(identifier, creditCardNumber, CreditCardBatchLineStatus.INVALID);
        }

    }
}
