package com.jmreisswitz.creditcards.domain.creditcard;

public record CreditCardData(
        CreditCardNumber number,
        CreditCardCvv cvv,
        ExpireDate expireDate,
        String lastFourDigits,
        CreditCardHolder holder
) {
}
