package com.bwie.okhttpdemo.model.user;

import com.bwie.okhttpdemo.api.UserApi;
import com.bwie.okhttpdemo.contract.UserContract;
import com.bwie.okhttpdemo.net.NetCallback;
import com.bwie.okhttpdemo.net.OkhttpUtils;

import java.util.HashMap;

/**
 * model层的作用是，处理数据的：网络请求
 */
public class UserModel implements UserContract.IUserModel {


    @Override
    public void reg(HashMap<String, String> params, NetCallback netCallback) {

        OkhttpUtils.getInstance().doPost(UserApi.REG_URL, params,netCallback);
    }

    @Override
    public void login(HashMap<String, String> params, NetCallback netCallback) {
        OkhttpUtils.getInstance().doPost(UserApi.LOGIN_URL, params,netCallback);
    }
}
