package com.mycp.mylibrary.utils;

import com.google.gson.annotations.SerializedName;


public class BaseResponse<T> implements ErrorStatus{

    @SerializedName("ret")
    public int status;
    @SerializedName("messagewocaonima")
    public String message;
    @SerializedName("data")
    public T data;

    /**
     * 是否请求成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return status == OK;
    }

    public boolean isTokenFail() {
        return status == 405;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
