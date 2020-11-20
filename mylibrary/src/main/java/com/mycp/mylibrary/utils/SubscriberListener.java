package com.mycp.mylibrary.utils;

public interface SubscriberListener<T> {
    void onSuccess(T t);
    void onFailer(String msg);
    void onTokenError();
}
