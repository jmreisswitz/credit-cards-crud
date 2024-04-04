package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.infrastructure.security.CreditCardDataEncoder;

import java.util.List;

public class EncodedCreditCardBatchRepository implements CreditCardBatchRepository {

    private final CreditCardBatchRepository delegate;
    private final CreditCardDataEncoder creditCardDataEncoder;

    public EncodedCreditCardBatchRepository(CreditCardBatchRepository delegate,
                                            CreditCardDataEncoder creditCardDataEncoder) {
        this.delegate = delegate;
        this.creditCardDataEncoder = creditCardDataEncoder;
    }

    @Override
    public void save(CreditCardBatch creditCardBatch) {
        var encodedCreditCardBatch = new CreditCardBatch(
                creditCardBatch.userId(),
                creditCardBatch.name(),
                creditCardBatch.date(),
                encodeCreditCards(creditCardBatch)
        );
        delegate.save(encodedCreditCardBatch);
    }

    private List<CreditCardBatch.Line> encodeCreditCards(CreditCardBatch creditCardBatch) {
        return creditCardBatch.creditCards().stream()
                .map(creditCardLine -> new CreditCardBatch.Line(
                        creditCardLine.identifier(),
                        creditCardDataEncoder.encode(creditCardLine.creditCardNumber()))).toList();
    }
}
