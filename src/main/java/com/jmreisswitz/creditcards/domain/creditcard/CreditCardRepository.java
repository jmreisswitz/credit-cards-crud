package com.jmreisswitz.creditcards.domain.creditcard;

import com.jmreisswitz.creditcards.domain.user.UserId;

import java.util.Collection;

public interface CreditCardRepository {

    CreditCard save(CreditCard creditCard);
    CreditCard findBy(CreditCardId id);
    Collection<CreditCard> findBy(UserId user);

}
