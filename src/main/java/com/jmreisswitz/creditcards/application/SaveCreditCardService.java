package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.SaveCreditCardDomainService;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardDataValidator;
import com.jmreisswitz.creditcards.infrastructure.security.CreditCardDataEncoder;

public class SaveCreditCardService {

    private final SaveCreditCardDomainService domainService;
    private final CreditCardDataEncoder creditCardDataEncoder;
    private final CreditCardDataValidator creditCardDataValidator;

    public SaveCreditCardService(SaveCreditCardDomainService domainService,
                                 CreditCardDataEncoder creditCardDataEncoder,
                                 CreditCardDataValidator creditCardDataValidator) {
        this.domainService = domainService;
        this.creditCardDataEncoder = creditCardDataEncoder;
        this.creditCardDataValidator = creditCardDataValidator;
    }

    public CreditCard save(CreditCard creditCard) {
        validate(creditCard);
        encode(creditCard);
        return domainService.save(creditCard);
    }

    private void validate(CreditCard creditCard) {
        creditCardDataValidator.validate(creditCard.data());
    }

    private void encode(CreditCard creditCard) {
        var encodedData = creditCardDataEncoder.encode(creditCard.data());
        creditCard.setData(encodedData);
    }
}
