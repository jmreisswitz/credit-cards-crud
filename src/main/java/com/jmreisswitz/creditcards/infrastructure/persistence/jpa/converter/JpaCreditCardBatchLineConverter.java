package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.converter;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchLineStatus;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaCreditCardBatch;
import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaCreditCardBatchLine;

public class JpaCreditCardBatchLineConverter {

    private JpaCreditCardBatchLineConverter() {
        // static class
    }

    public static CreditCardBatch.Line toModel(JpaCreditCardBatchLine jpaCreditCardBatchLine) {
        return new CreditCardBatch.Line(
                jpaCreditCardBatchLine.getIdentifier(),
                new CreditCardNumber(jpaCreditCardBatchLine.getCreditCardNumber()),
                jpaCreditCardBatchLine.getLastFourDigits(),
                CreditCardBatchLineStatus.valueOf(jpaCreditCardBatchLine.getStatus())
        );
    }

    public static JpaCreditCardBatchLine toJpa(CreditCardBatch.Line creditCardBatchLine,
                                               JpaCreditCardBatch creditCardBatch) {
        JpaCreditCardBatchLine jpaCreditCardBatchLine = new JpaCreditCardBatchLine();
        jpaCreditCardBatchLine.setIdentifier(creditCardBatchLine.identifier());
        jpaCreditCardBatchLine.setCreditCardNumber(creditCardBatchLine.creditCardNumber().value());
        jpaCreditCardBatchLine.setLastFourDigits(creditCardBatchLine.lastFourDigits());
        jpaCreditCardBatchLine.setStatus(creditCardBatchLine.status().toString());
        jpaCreditCardBatchLine.setCreditCardBatch(creditCardBatch);
        return jpaCreditCardBatchLine;
    }

}
