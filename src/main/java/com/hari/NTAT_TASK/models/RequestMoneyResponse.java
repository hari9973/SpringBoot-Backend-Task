package com.hari.NTAT_TASK.models;

public class RequestMoneyResponse {

    private String message;

    public RequestMoneyResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
