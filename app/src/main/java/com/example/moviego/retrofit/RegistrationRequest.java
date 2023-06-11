package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    // Constructor
    public RegistrationRequest(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    // Getters and setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}