package com.bwie.okhttpdemo.contract;

import com.bwie.okhttpdemo.net.NetCallback;

import java.util.HashMap;

/**
 * 用户系统,契约类统一管理，google真正mvp的
 */
public interface UserContract {

    interface IUserModel{
        void reg(HashMap<String,String> params, NetCallback netCallback);
        void login(HashMap<String,String> params,NetCallback netCallback);
    }
    interface IUserView{
        void success(String result);
        void failure(String msg);

    }
    interface IUserPresenter{
        void reg(HashMap<String,String> params);
        void login(HashMap<String,String> params);
    }
}
