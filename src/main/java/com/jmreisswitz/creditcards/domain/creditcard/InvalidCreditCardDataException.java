package com.jmreisswitz.creditcards.domain.creditcard;

public class InvalidCreditCardDataException extends RuntimeException {
    public InvalidCreditCardDataException(String message) {
        super(message);
    }
}
