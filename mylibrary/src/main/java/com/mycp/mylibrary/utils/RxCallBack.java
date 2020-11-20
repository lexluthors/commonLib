package com.mycp.mylibrary.utils;


public interface RxCallBack<T> {

    T doSomeThing();

    void callBackUI(T t);
}
