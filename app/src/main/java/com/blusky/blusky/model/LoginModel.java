package com.blusky.blusky.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("error")
    @Expose
    private LoginError error;




    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginError getLoginError() {
        return error;
    }

    public void setLoginError(LoginError error) {
        this.error = error;
    }
}
