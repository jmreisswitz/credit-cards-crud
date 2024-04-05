package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.converter;

import com.jmreisswitz.creditcards.domain.creditcard.*;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaCreditCard;

public class JpaCreditCardConverter {

    private JpaCreditCardConverter() {
        // static class
    }

    public static CreditCard toCreditCard(JpaCreditCard jpaCreditCard) {
        if (jpaCreditCard == null) {
            return null;
        }
        return new CreditCard(
            new CreditCardId(jpaCreditCard.getId()),
            new UserId(jpaCreditCard.getUserId()),
                convertCreditCardData(jpaCreditCard));
    }

    private static CreditCardData convertCreditCardData(JpaCreditCard jpaCreditCard) {
        return new CreditCardData(
                new CreditCardNumber(jpaCreditCard.getNumber()),
                jpaCreditCard.getCvv() == null ? null : new CreditCardCvv(jpaCreditCard.getCvv()),
                getExpireDate(jpaCreditCard),
                jpaCreditCard.getLastFourDigits(),
                jpaCreditCard.getHolder() == null ? null : new CreditCardHolder(jpaCreditCard.getHolder()));
    }

    private static ExpireDate getExpireDate(JpaCreditCard jpaCreditCard) {
        if (jpaCreditCard.getExpireDateMonth() == null || jpaCreditCard.getExpireDateYear() == null) {
            return null;
        }
        return new ExpireDate(
                Integer.parseInt(jpaCreditCard.getExpireDateYear()),
                Integer.parseInt(jpaCreditCard.getExpireDateMonth()));
    }

    public static JpaCreditCard toJpaCreditCard(CreditCard creditCard) {
        return new JpaCreditCard(
                creditCard.id() == null ? null : creditCard.id().value(),
                creditCard.data().number().value(),
                creditCard.data().holder() == null ? null : creditCard.data().holder().name(),
                creditCard.data().expireDate() == null ? null : String.valueOf(creditCard.data().expireDate().month()),
                creditCard.data().expireDate() == null ? null : String.valueOf(creditCard.data().expireDate().year()),
                creditCard.data().lastFourDigits(),
                creditCard.data().cvv() == null? null : creditCard.data().cvv().value(),
                creditCard.userId().value());
    }

}
