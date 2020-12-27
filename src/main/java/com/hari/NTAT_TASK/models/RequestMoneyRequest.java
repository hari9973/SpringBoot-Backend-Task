package com.hari.NTAT_TASK.models;

public class RequestMoneyRequest {

    private String payer;

    private String recipient;

    private String amount;

    public RequestMoneyRequest(String payer, String recipient,String amount) {
        this.payer = payer;
        this.recipient = recipient;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
}
