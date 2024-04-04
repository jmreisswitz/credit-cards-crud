package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardNumberValidator;

public class SaveCreditCardBatchService {

    private final CreditCardBatchRepository creditCardBatchRepository;
    private final CreditCardNumberValidator creditCardNumberValidator;

    public SaveCreditCardBatchService(CreditCardBatchRepository creditCardBatchRepository,
                                      CreditCardNumberValidator creditCardNumberValidator) {
        this.creditCardBatchRepository = creditCardBatchRepository;
        this.creditCardNumberValidator = creditCardNumberValidator;
    }

    public void saveCreditCardBatch(CreditCardBatch creditCardBatch) {
        creditCardBatch.validateLines(creditCardNumberValidator);
        creditCardBatchRepository.save(creditCardBatch);
    }
}
