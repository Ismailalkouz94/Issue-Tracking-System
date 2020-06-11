package com.ismail.its.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponseMessage extends BaseResponseMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("response")
    private LoginResponse response;

    public LoginResponseMessage(boolean isSuccess, LoginResponse response, String errMsg, String errDetail, String successMsg, LoginResponse response1) {
        super(isSuccess, response, errMsg, errDetail, successMsg);
        this.response = response1;
    }

    public LoginResponse getResponse() {
        return response;
    }

    public void setResponse(LoginResponse response) {
        this.response = response;
    }
}