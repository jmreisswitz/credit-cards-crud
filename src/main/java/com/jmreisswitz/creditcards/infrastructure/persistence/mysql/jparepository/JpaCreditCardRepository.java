package com.jmreisswitz.creditcards.infrastructure.persistence.mysql.jparepository;

import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.entity.JpaCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JpaCreditCardRepository extends JpaRepository<JpaCreditCard, Integer> {
    Collection<JpaCreditCard> findByUserId(Integer userId);
}
