package com.jmreisswitz.creditcards.domain.creditcard;

public class SaveCreditCardDomainService {
    private final CreditCardRepository creditCardRepository;


    public SaveCreditCardDomainService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }
}
