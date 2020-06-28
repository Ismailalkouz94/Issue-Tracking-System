package com.ismail.its.model.response;

import com.google.gson.annotations.SerializedName;
import com.ismail.its.model.Issues;
import com.ismail.its.model.User;

import java.io.Serializable;
import java.util.List;

public class IssueResponse extends BaseResponseMessage implements Serializable {

    @SerializedName("response")
    private List<Issues> response;

    public IssueResponse(boolean isSuccess, LoginResponse response, String errMsg, String errDetail, String successMsg, List<Issues> response1) {
        super(isSuccess, response, errMsg, errDetail, successMsg);
        this.response = response1;
    }

    public List<Issues> getResponse() {
        return response;
    }

    public void setResponse(List<Issues> response) {
        this.response = response;
    }
}