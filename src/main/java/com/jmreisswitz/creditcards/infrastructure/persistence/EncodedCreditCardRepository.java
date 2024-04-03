package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardId;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.infrastructure.security.CreditCardDataEncoder;

import java.util.Collection;

public class EncodedCreditCardRepository implements CreditCardRepository {

    private final CreditCardRepository delegate;
    private final CreditCardDataEncoder creditCardDataEncoder;

    public EncodedCreditCardRepository(CreditCardRepository creditCardRepository,
                                       CreditCardDataEncoder creditCardDataEncoder) {
        this.delegate = creditCardRepository;
        this.creditCardDataEncoder = creditCardDataEncoder;
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        var encodedCreditCardData = creditCardDataEncoder.encode(creditCard.data());
        var encodedCreditCard = new CreditCard(creditCard.userId(), encodedCreditCardData);
        return delegate.save(encodedCreditCard);
    }

    @Override
    public CreditCard findBy(CreditCardId id) {
        return delegate.findBy(id);
    }

    @Override
    public Collection<CreditCard> findBy(UserId user) {
        return delegate.findBy(user);
    }
}
