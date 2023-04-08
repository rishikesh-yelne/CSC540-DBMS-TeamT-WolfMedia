package edu.ncsu.csc540.s23.backend.model.dto;

import java.sql.Date;

public class PodcastHostPaymentDTO {
    private String podcastHost;
    private Double amount;
    private Date paymentDate;

    public String getPodcastHost() {
        return podcastHost;
    }

    public void setPodcastHost(String podcastHost) {
        this.podcastHost = podcastHost;
    }


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
