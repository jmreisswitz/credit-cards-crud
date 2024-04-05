package com.jmreisswitz.creditcards.infrastructure.persistence.jpa;

import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchLineStatus;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.converter.JpaCreditCardBatchConverter;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaCreditCardBatch;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository.JpaCreditCardBatchRepository;

import java.util.Collection;

public class JpaAdapterCreditCardBatchRepository implements CreditCardBatchRepository {

    private final JpaCreditCardBatchRepository jpaCreditCardBatchRepository;

    public JpaAdapterCreditCardBatchRepository(JpaCreditCardBatchRepository jpaCreditCardBatchRepository) {
        this.jpaCreditCardBatchRepository = jpaCreditCardBatchRepository;
    }

    @Override
    public void save(CreditCardBatch creditCardBatch) {
        JpaCreditCardBatch jpaBatch = JpaCreditCardBatchConverter.toJpa(creditCardBatch);
        jpaCreditCardBatchRepository.save(jpaBatch);
    }

    @Override
    public Collection<CreditCardBatch> findNotCompleted() {
        Collection<JpaCreditCardBatch> jpaBatches = jpaCreditCardBatchRepository
                .findByCreditCardBatchLinesStatus(CreditCardBatchLineStatus.TO_BE_PROCESSED.toString());
        return jpaBatches.stream().map(JpaCreditCardBatchConverter::toModel).toList();
    }
}
