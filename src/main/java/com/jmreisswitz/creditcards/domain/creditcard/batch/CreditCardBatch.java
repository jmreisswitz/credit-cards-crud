package com.jmreisswitz.creditcards.domain.creditcard.batch;

import com.jmreisswitz.creditcards.domain.creditcard.*;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardNumberValidator;
import com.jmreisswitz.creditcards.domain.user.UserId;

import java.time.LocalDate;
import java.util.Collection;

import static com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchLineStatus.TO_BE_PROCESSED;

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

    public void process(SaveCreditCardDomainService saveCreditCardService) {
        creditCards().stream()
                .filter(line -> line.status() == TO_BE_PROCESSED)
                .forEach(line -> line.process(userId, saveCreditCardService));
    }

    public boolean isCompleted() {
        return creditCards.stream()
                .allMatch(line -> line.status() != CreditCardBatchLineStatus.TO_BE_PROCESSED);
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

    public static class Line {
        private final String identifier;
        private final CreditCardNumber creditCardNumber;
        private final String lastFourDigits;
        private CreditCardBatchLineStatus status;

        public Line(String identifier,
                    CreditCardNumber creditCardNumber,
                    String lastFourDigits,
                    CreditCardBatchLineStatus status) {
            this.identifier = identifier;
            this.creditCardNumber = creditCardNumber;
            this.lastFourDigits = lastFourDigits;
            this.status = status;
        }

        public boolean isValid(CreditCardNumberValidator creditCardNumberValidator) {
            try {
                creditCardNumberValidator.validate(creditCardNumber);
                return true;
            } catch (InvalidCreditCardDataException e) {
                return false;
            }
        }

        public Line asInvalid() {
            return new Line(identifier, creditCardNumber, lastFourDigits, CreditCardBatchLineStatus.INVALID);
        }

        public void setAsProcessed() {
            status = CreditCardBatchLineStatus.PROCESSED;
        }

        public void process(UserId userId, SaveCreditCardDomainService saveCreditCardService) {
            saveAsCreditCard(userId, saveCreditCardService);
            setAsProcessed();
        }

        private void saveAsCreditCard(UserId userId, SaveCreditCardDomainService saveCreditCardService) {
            saveCreditCardService.save(new CreditCard(userId,
                    new CreditCardData(creditCardNumber, null, null, lastFourDigits, null)
            ));
        }

        public String identifier() {
            return identifier;
        }

        public CreditCardNumber creditCardNumber() {
            return creditCardNumber;
        }

        public String lastFourDigits() {
            return lastFourDigits;
        }

        public CreditCardBatchLineStatus status() {
            return status;
        }
    }
}
