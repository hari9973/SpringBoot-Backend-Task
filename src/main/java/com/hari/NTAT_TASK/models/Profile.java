package com.hari.NTAT_TASK.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class Profile {

    private String image;

    private String balance;


    public Profile(String image, String balance) {
        this.image = image;
        this.balance = balance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
