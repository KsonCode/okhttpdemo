package com.bwie.okhttpdemo.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.okhttpdemo.R;
import com.bwie.okhttpdemo.contract.UserContract;
import com.bwie.okhttpdemo.entity.UserInfoEntity;
import com.bwie.okhttpdemo.presenter.user.UserRegPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class RegActivity extends AppCompatActivity implements View.OnClickListener, UserContract.IUserView {
    private Button regBtn;
    private UserRegPresenter userRegPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initData();

    }

    /**
     *
     */
    private void initData() {
        userRegPresenter = new UserRegPresenter(this);
        EventBus.getDefault().register(this);


    }


    /**
     *
     */
    private void initView() {
        regBtn = findViewById(R.id.btn_reg);
        regBtn.setOnClickListener(this);

    }

    @Subscribe(threadMode = ThreadMode.PostThread)
    public void reiceive(String phone){
        System.out.println("tname2==="+Thread.currentThread().getName());
        ;
        System.out.println("regphone==="+phone);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_reg:
                reg();
                break;

        }



    }

    /**
     * 注册业务逻辑
     */
    private void reg() {
        HashMap<String,String> params = new HashMap<>();
        params.put("phone","18989898989");
        params.put("pwd","111111");
        userRegPresenter.reg(params);




    }

    /**
     * 注册成功回调
     * @param result
     */
    @Override
    public void success(String result) {

//        UserInfoEntity userInfoEntity = new Gson().fromJson(result, UserInfoEntity.class);

        final UserInfoEntity.Result result1 = new UserInfoEntity.Result();
        result1.phone = "333333333";
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadname1==="+Thread.currentThread().getName());
                ;
                EventBus.getDefault().postSticky("186120019990");//post发送事件的方法，普通事件
                EventBus.getDefault().postSticky("3232323");//post发送事件的方法，普通事件
//        //此事件是一个string对象
                EventBus.getDefault().post(result1);//post发送事件的方法
                //此事件是一个string对象
            }
        }).start();


        startActivity(new Intent(this,LoginActivity.class));


        Toast.makeText(this,result.toString(),Toast.LENGTH_SHORT).show();



    }



    @Override
    public void failure(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (userRegPresenter!=null){
            userRegPresenter.detach();//回收资源
        }
    }
}
