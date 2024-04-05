package com.jmreisswitz.creditcards.infrastructure.persistence.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "credit_card")
public class JpaCreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String number;
    private String holder;
    private String expireDateMonth;
    private String expireDateYear;
    private String lastFourDigits;
    private String cvv;

    public JpaCreditCard() {
    }

    public JpaCreditCard(Integer id, String number, String holder,
                         String expireDateMonth, String expireDateYear,
                         String lastFourDigits, String cvv, Integer userId) {
        this.id = id;
        this.number = number;
        this.holder = holder;
        this.expireDateMonth = expireDateMonth;
        this.lastFourDigits = lastFourDigits;
        this.cvv = cvv;
        this.userId = userId;
        this.expireDateYear = expireDateYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpireDateMonth() {
        return expireDateMonth;
    }

    public void setExpireDateMonth(String expirationDateMonth) {
        this.expireDateMonth = expirationDateMonth;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public String getExpireDateYear() {
        return expireDateYear;
    }

    public void setExpireDateYear(String expireDateYear) {
        this.expireDateYear = expireDateYear;
    }
}
