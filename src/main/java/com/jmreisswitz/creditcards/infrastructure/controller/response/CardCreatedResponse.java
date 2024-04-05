package com.jmreisswitz.creditcards.infrastructure.controller.response;

import java.io.Serializable;

public class CardCreatedResponse implements Serializable {

    private Integer cardId;

    public CardCreatedResponse(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
