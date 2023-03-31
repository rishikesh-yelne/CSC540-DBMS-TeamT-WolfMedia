package edu.ncsu.csc540.s23.backend.model.relationships;

import java.time.Instant;

public class UserPays {

    private Long userId;

    private Long transactionId;

    private String plan;

    private Double amount;

    private Integer numOfMonths;

    private Instant startDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNumOfMonths() {
        return numOfMonths;
    }

    public void setNumOfMonths(Integer numOfMonths) {
        this.numOfMonths = numOfMonths;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
}
