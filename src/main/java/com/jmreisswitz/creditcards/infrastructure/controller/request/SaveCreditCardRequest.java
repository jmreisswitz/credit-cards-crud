package com.jmreisswitz.creditcards.infrastructure.controller.request;

import com.jmreisswitz.creditcards.domain.creditcard.*;

public class SaveCreditCardRequest {
    private String number;
    private String cvv;
    private int expireMonth;
    private int expireYear;
    private String holderName;

    public SaveCreditCardRequest() {
        // used for serialization
    }

    public CreditCardData asCreditCardData() {
        return new CreditCardData(
                new CreditCardNumber(number),
                new CreditCardCvv(cvv),
                new ExpireDate(expireYear, expireMonth),
                number.substring(number.length() - 4),
                new CreditCardHolder(holderName)
        );
    }

    public String number() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String cvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int expireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(int expireMonth) {
        this.expireMonth = expireMonth;
    }

    public int expireYear() {
        return expireYear;
    }

    public void setExpireYear(int expireYear) {
        this.expireYear = expireYear;
    }

    public String holderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
