package com.jmreisswitz.creditcards.domain.creditcard.batch;

import java.util.Collection;

public interface CreditCardBatchRepository {

    void save(CreditCardBatch creditCardBatch);
    Collection<CreditCardBatch> findNotCompleted();
}
