package com.bwie.okhttpdemo.net;

public interface NetCallback {
    void success(String result);
    void failure(String error);
}
