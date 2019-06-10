package com.bwie.okhttpdemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.okhttpdemo.R;
import com.bwie.okhttpdemo.entity.UserInfoEntity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class LoginActivity extends AppCompatActivity {

    private TextView phoneTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);//订阅当前对象到eventbus里面管理
        phoneTv = findViewById(R.id.tv_phone);

    }

    /**
     * 接收发送过来的数据
     */
    @Subscribe(sticky = true)//粘性事件属性，设置为true，默认false
    public void receiveUserInfo(String phone){

        System.out.println("threadname2==="+Thread.currentThread().getName());
        ;

//        System.out.println("phone1==="+phone);
        Toast.makeText(this,phone+"",Toast.LENGTH_SHORT).show();




    }
    /**
     * 接收发送过来的数据,通过eventbus注解去接收事件（对象，数据），通过参数类型判断发送来源
     */
    @Subscribe
    public void receiveUserInfo(UserInfoEntity.Result userInfoEntity){
        System.out.println("phone2==="+userInfoEntity.phone);
    }


    /**
     *
     * @param view
     */
    public void login(View view) {
        EventBus.getDefault().post("1111111");
        System.out.println("tname1==="+Thread.currentThread().getName());
        ;
        this.finish();
    }
}
