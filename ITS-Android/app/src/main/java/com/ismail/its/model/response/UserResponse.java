package com.ismail.its.model.response;

import com.google.gson.annotations.SerializedName;
import com.ismail.its.model.User;

import java.io.Serializable;
import java.util.List;

public class UserResponse extends BaseResponseMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("response")
    private List<User> response;

    public UserResponse(boolean isSuccess, LoginResponse response, String errMsg, String errDetail, String successMsg, List<User> response1) {
        super(isSuccess, response, errMsg, errDetail, successMsg);
        this.response = response1;
    }

    public List<User> getResponse() {
        return response;
    }

    public void setResponse(List<User> response) {
        this.response = response;
    }
}