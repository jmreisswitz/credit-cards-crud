package com.jmreisswitz.creditcards.infrastructure.controller.response;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;

public class CreditCardView {

    private Integer id;
    private String lastFourDigits;

    public static CreditCardView from(CreditCard creditCard) {
        return new CreditCardView(creditCard.id().value(), creditCard.data().lastFourDigits());
    }

    public CreditCardView(Integer id, String lastFourDigits) {
        this.id = id;
        this.lastFourDigits = lastFourDigits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }
}
