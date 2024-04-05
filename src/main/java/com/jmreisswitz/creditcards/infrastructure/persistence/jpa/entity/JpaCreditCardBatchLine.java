package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity;


import jakarta.persistence.*;

@Entity(name = "credit_card_batch_line")
public class JpaCreditCardBatchLine {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String identifier;

    private String creditCardNumber;
    private String lastFourDigits;
    private String status;

    @ManyToOne
    private JpaCreditCardBatch creditCardBatch;

    public JpaCreditCardBatchLine() {
        // used by JPA
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JpaCreditCardBatch getCreditCardBatch() {
        return creditCardBatch;
    }

    public void setCreditCardBatch(JpaCreditCardBatch creditCardBatch) {
        this.creditCardBatch = creditCardBatch;
    }
}
