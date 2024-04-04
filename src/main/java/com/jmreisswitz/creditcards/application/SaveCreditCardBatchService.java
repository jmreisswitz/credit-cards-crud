package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;

public class SaveCreditCardBatchService {

    private final CreditCardBatchRepository creditCardBatchRepository;

    public SaveCreditCardBatchService(CreditCardBatchRepository creditCardBatchRepository) {
        this.creditCardBatchRepository = creditCardBatchRepository;
    }

    public void saveCreditCardBatch(CreditCardBatch creditCardBatch) {
        creditCardBatchRepository.save(creditCardBatch);
    }
}
