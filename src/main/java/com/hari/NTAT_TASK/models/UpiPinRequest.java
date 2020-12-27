package com.hari.NTAT_TASK.models;

public class UpiPinRequest {

    private String pin;

    private String username;

    public UpiPinRequest(String pin,String username) {
        this.pin = pin;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
