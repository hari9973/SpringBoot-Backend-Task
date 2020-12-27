package com.hari.NTAT_TASK.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="requestmoney")
public class RequestMoney implements Comparable<RequestMoney>{

    @Id
    private String id;

    private String payer;

    private String recipient;

    private String amount;

    private Date createdOn;

    public RequestMoney(String payer, String recipient,String amount,Date createdOn) {
        this.payer = payer;
        this.recipient = recipient;
        this.amount = amount;
        this.createdOn = createdOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public int compareTo(RequestMoney u) {
        if (getCreatedOn() == null || u.getCreatedOn() == null) {
            return 0;
        }
        return getCreatedOn().compareTo(u.getCreatedOn());
    }
}
