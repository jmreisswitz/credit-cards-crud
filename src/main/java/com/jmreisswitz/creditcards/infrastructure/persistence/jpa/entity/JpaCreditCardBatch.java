package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "credit_card_batch")
public class JpaCreditCardBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String name;
    private LocalDate date;

    @OneToMany(mappedBy = "creditCardBatch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<JpaCreditCardBatchLine> creditCardBatchLines;

    public JpaCreditCardBatch() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<JpaCreditCardBatchLine> getCreditCardBatchLines() {
        return creditCardBatchLines;
    }

    public void setCreditCardBatchLines(List<JpaCreditCardBatchLine> creditCardBatchLines) {
        this.creditCardBatchLines = creditCardBatchLines;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
