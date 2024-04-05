package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.converter;

import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaCreditCardBatch;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaCreditCardBatchLine;

import java.util.List;

public class JpaCreditCardBatchConverter {

    private JpaCreditCardBatchConverter() {
        // static class
    }

    public static CreditCardBatch toModel(JpaCreditCardBatch jpaCreditCardBatch) {
        return new CreditCardBatch(
                jpaCreditCardBatch.getId(),
                new UserId(jpaCreditCardBatch.getUserId()),
                jpaCreditCardBatch.getName(),
                jpaCreditCardBatch.getDate(),
                jpaCreditCardBatch.getCreditCardBatchLines().stream()
                        .map(JpaCreditCardBatchLineConverter::toModel)
                        .toList());
    }

    public static JpaCreditCardBatch toJpa(CreditCardBatch creditCardBatch) {
        JpaCreditCardBatch jpaCreditCardBatch = new JpaCreditCardBatch();
        if (creditCardBatch.id() != null) {
            jpaCreditCardBatch.setId(creditCardBatch.id());
        }
        jpaCreditCardBatch.setUserId(creditCardBatch.userId().value());
        jpaCreditCardBatch.setName(creditCardBatch.name());
        jpaCreditCardBatch.setDate(creditCardBatch.date());
        jpaCreditCardBatch.setCreditCardBatchLines(getCreditCardBatchLines(creditCardBatch, jpaCreditCardBatch));
        return jpaCreditCardBatch;
    }

    private static List<JpaCreditCardBatchLine> getCreditCardBatchLines(CreditCardBatch creditCardBatch,
                                                                        JpaCreditCardBatch jpaCreditCardBatch) {
        return creditCardBatch.creditCards().stream()
                .map(line -> JpaCreditCardBatchLineConverter.toJpa(line, jpaCreditCardBatch))
                .toList();
    }

}
