package com.hari.NTAT_TASK.models;

public class UpiPinResponse {

    private Boolean isValid;

    public UpiPinResponse(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
