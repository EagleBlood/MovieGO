package com.example.moviego.retrofit;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public RegistrationResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}