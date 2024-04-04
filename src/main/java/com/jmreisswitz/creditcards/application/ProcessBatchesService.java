package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.SaveCreditCardDomainService;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;

import java.util.Collection;

public class ProcessBatchesService {

    private final CreditCardBatchRepository creditCardBatchRepository;
    private final SaveCreditCardDomainService saveCreditCardService;

    public ProcessBatchesService(CreditCardBatchRepository creditCardBatchRepository,
                                 SaveCreditCardDomainService saveCreditCardService) {
        this.creditCardBatchRepository = creditCardBatchRepository;
        this.saveCreditCardService = saveCreditCardService;
    }

    public void processNotCompletedBatches() {
        Collection<CreditCardBatch> notCompletedBatches = creditCardBatchRepository.findNotCompleted();
        notCompletedBatches.forEach(this::processCreditCardBatch);
    }

    private void processCreditCardBatch(CreditCardBatch creditCardBatch) {
        creditCardBatch.process(saveCreditCardService);
        creditCardBatchRepository.save(creditCardBatch);
    }

}
