package edu.ncsu.csc540.s23.backend.model.dto;

import java.sql.Date;

public class RevenueDTO {
    private Double amount;
    private Date paymentDate;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
