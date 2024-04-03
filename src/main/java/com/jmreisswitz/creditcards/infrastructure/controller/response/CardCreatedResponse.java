package com.jmreisswitz.creditcards.infrastructure.controller.response;

import java.io.Serializable;

public class CardCreatedResponse implements Serializable {

    private Long cardId;

    public CardCreatedResponse(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
