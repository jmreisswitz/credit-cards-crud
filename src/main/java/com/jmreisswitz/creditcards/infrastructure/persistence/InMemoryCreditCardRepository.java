package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardId;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.user.UserId;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryCreditCardRepository implements CreditCardRepository {

    private final Collection<CreditCard> creditCards = new ArrayList<>();
    private Long counter = 0L;

    @Override
    public CreditCard save(CreditCard creditCard) {
        creditCard.setId(new CreditCardId(counter++));
        creditCards.add(creditCard);
        return creditCard;
    }

    @Override
    public CreditCard findBy(CreditCardId id) {
        return creditCards.stream()
                .filter(creditCard -> creditCard.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<CreditCard> findBy(UserId user) {
        return creditCards.stream()
                .filter(creditCard -> creditCard.userId().equals(user))
                .toList();
    }
}
