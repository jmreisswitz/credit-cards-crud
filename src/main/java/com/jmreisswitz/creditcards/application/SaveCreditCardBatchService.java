package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardNumberValidator;
import com.jmreisswitz.creditcards.infrastructure.security.CreditCardDataEncoder;

import java.util.List;

public class SaveCreditCardBatchService {

    private final CreditCardBatchRepository creditCardBatchRepository;
    private final CreditCardNumberValidator creditCardNumberValidator;
    private final CreditCardDataEncoder creditCardDataEncoder;

    public SaveCreditCardBatchService(CreditCardBatchRepository creditCardBatchRepository,
                                      CreditCardNumberValidator creditCardNumberValidator,
                                      CreditCardDataEncoder creditCardDataEncoder) {
        this.creditCardBatchRepository = creditCardBatchRepository;
        this.creditCardNumberValidator = creditCardNumberValidator;
        this.creditCardDataEncoder = creditCardDataEncoder;
    }

    public void saveCreditCardBatch(CreditCardBatch creditCardBatch) {
        creditCardBatch.validateLines(creditCardNumberValidator);
        var encodedCreditCards = encodeCreditCards(creditCardBatch);
        creditCardBatch.setCreditCards(encodedCreditCards);
        creditCardBatchRepository.save(creditCardBatch);
    }

    private List<CreditCardBatch.Line> encodeCreditCards(CreditCardBatch creditCardBatch) {
        return creditCardBatch.creditCards().stream()
                .map(creditCardLine -> new CreditCardBatch.Line(
                        creditCardLine.identifier(),
                        creditCardDataEncoder.encode(creditCardLine.creditCardNumber()),
                        creditCardLine.lastFourDigits(),
                        creditCardLine.status())).toList();
    }
}
