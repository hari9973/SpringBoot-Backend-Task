package com.hari.NTAT_TASK.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="transactions")
public class Transactions implements Comparable<Transactions>{

    @Id
    private String id;

    private String sender;

    private String receiver;

    private String amount;

    private String createdOn;

    public Transactions(String sender, String receiver, String amount,String createdOn) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.createdOn = createdOn;
    }


    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Transactions u) {
        if (getCreatedOn() == null || u.getCreatedOn() == null) {
            return 0;
        }
        return getCreatedOn().compareTo(u.getCreatedOn());
    }
}
