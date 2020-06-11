package com.ismail.its.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponseMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("isSuccess")
    protected boolean isSuccess;
    @SerializedName("errMsg")
    protected String errMsg;
    @SerializedName("errDetail")
    protected String errDetail;
    @SerializedName("successMsg")
    protected String successMsg;

    public BaseResponseMessage(boolean isSuccess, LoginResponse response, String errMsg, String errDetail, String successMsg) {
        this.isSuccess = isSuccess;
        this.errMsg = errMsg;
        this.errDetail = errDetail;
        this.successMsg = successMsg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrDetail() {
        return errDetail;
    }

    public void setErrDetail(String errDetail) {
        this.errDetail = errDetail;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}