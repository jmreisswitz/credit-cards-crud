package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCardRepository;
import com.jmreisswitz.creditcards.domain.user.UserId;

import java.util.Collection;

public class RetrieveCreditCardService {

    private final CreditCardRepository creditCardRepository;

    public RetrieveCreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public Collection<CreditCard> retrieveByUserId(UserId userId) {
        return creditCardRepository.findBy(userId);
    }

}
