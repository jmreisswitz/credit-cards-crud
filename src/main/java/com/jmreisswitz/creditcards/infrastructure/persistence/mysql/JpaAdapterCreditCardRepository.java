package com.jmreisswitz.creditcards.infrastructure.persistence.mysql;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardId;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.converter.JpaCreditCardConverter;
import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.entity.JpaCreditCard;
import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.jparepository.JpaCreditCardRepository;

import java.util.Collection;

public class JpaAdapterCreditCardRepository implements CreditCardRepository {

    private final JpaCreditCardRepository jpaCreditCardRepository;

    public JpaAdapterCreditCardRepository(JpaCreditCardRepository jpaCreditCardRepository) {
        this.jpaCreditCardRepository = jpaCreditCardRepository;
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        JpaCreditCard jpaCreditCardToSave = JpaCreditCardConverter.toJpaCreditCard(creditCard);
        JpaCreditCard saved = jpaCreditCardRepository.save(jpaCreditCardToSave);
        return JpaCreditCardConverter.toCreditCard(saved);
    }

    @Override
    public CreditCard findBy(CreditCardId id) {
        JpaCreditCard jpaCreditCard = jpaCreditCardRepository.findById(id.value()).orElse(null);
        return JpaCreditCardConverter.toCreditCard(jpaCreditCard);
    }

    @Override
    public Collection<CreditCard> findBy(UserId user) {
        Collection<JpaCreditCard> jpaCreditCards = jpaCreditCardRepository.findByUserId(user.value());
        return jpaCreditCards.stream()
                .map(JpaCreditCardConverter::toCreditCard)
                .toList();
    }
}
