package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/12/2017.
 */

public class BaseMeta {
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
