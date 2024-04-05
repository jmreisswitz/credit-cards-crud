package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository;

import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaCreditCardBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JpaCreditCardBatchRepository extends JpaRepository<JpaCreditCardBatch, Integer> {
    Collection<JpaCreditCardBatch> findByCreditCardBatchLinesStatus(String status);
}
