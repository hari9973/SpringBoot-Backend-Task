package com.hari.NTAT_TASK.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection="users")
public class Users {
    @Id
    private String id;

    private String username;

    private String password;

    private String upi_pin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Profile profile;

    public Users(String username, String password,String upi_pin) {
        this.username = username;
        this.password = password;
        this.upi_pin = upi_pin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUpi_pin() {
        return upi_pin;
    }

    public void setUpi_pin(String upi_pin) {
        this.upi_pin = upi_pin;
    }
}
