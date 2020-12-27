package com.hari.NTAT_TASK.models;

public class TransactionResponse {
    private String transactionId;

    private String message;

    public TransactionResponse(String transactionId, String message) {
        this.transactionId = transactionId;
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMessage() {
        return message;
    }
}
