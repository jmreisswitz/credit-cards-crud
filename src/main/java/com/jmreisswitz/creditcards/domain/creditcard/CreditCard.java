package com.jmreisswitz.creditcards.domain.creditcard;

import com.jmreisswitz.creditcards.domain.user.UserId;

public class CreditCard {

    private CreditCardId id;
    private final UserId userId;
    private CreditCardData data;

    public CreditCard(UserId userId, CreditCardData data) {
        this.userId = userId;
        this.data = data;
    }

    public CreditCard(CreditCardId id, UserId userId, CreditCardData data) {
        this.id = id;
        this.userId = userId;
        this.data = data;
    }

    public void setId(CreditCardId id) {
        this.id = id;
    }

    public CreditCardId id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public void setData(CreditCardData data) {
        this.data = data;
    }

    public CreditCardData data() {
        return data;
    }
}
