package com.hari.NTAT_TASK.models;

public class AuthenticationResponse {

    private final String jwt;

    private final String error;

    public AuthenticationResponse(String jwt,String error) {
        this.jwt = jwt;
        this.error = error;
    }

    public String getJwt() {
        return jwt;
    }

    public String getError() {
        return error;
    }
}
