package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryCreditCardBatchRepository implements CreditCardBatchRepository {

    private final Collection<CreditCardBatch> creditCardBatches;

    public InMemoryCreditCardBatchRepository() {
        this.creditCardBatches = new ArrayList<>();
    }

    @Override
    public void save(CreditCardBatch creditCardBatch) {
        creditCardBatches.add(creditCardBatch);
    }

    @Override
    public Collection<CreditCardBatch> findNotCompleted() {
        return creditCardBatches.stream()
                .filter(creditCardBatch -> !creditCardBatch.isCompleted())
                .toList();
    }
}
