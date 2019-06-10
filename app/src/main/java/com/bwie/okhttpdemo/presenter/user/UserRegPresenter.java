package com.bwie.okhttpdemo.presenter.user;

import com.bwie.okhttpdemo.contract.UserContract;
import com.bwie.okhttpdemo.model.user.UserModel;
import com.bwie.okhttpdemo.net.NetCallback;

import java.util.HashMap;

public class UserRegPresenter implements UserContract.IUserPresenter {

    private UserModel userModel;
    private UserContract.IUserView iUserRegView;
    public UserRegPresenter(UserContract.IUserView userView){
        this.iUserRegView = userView;
        userModel = new UserModel();

    }
    @Override
    public void reg(HashMap<String, String> params) {

        userModel.reg(params, new NetCallback() {
            @Override
            public void success(String result) {
                iUserRegView.success(result);
            }

            @Override
            public void failure(String error) {
                iUserRegView.failure(error);

            }
        });

    }

    @Override
    public void login(HashMap<String, String> params) {



    }

    /**
     * 解绑，回收内存，防止内存泄漏
     */
    public void detach(){
        if (iUserRegView!=null){
            iUserRegView = null;
        }
        if (userModel!=null){
            userModel = null;
        }

        System.gc();//通知系统gc垃圾回收机制，回收的是整个app的垃圾对象，不是只回收当前对象

    }
}
