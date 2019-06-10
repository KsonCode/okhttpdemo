package com.bwie.okhttpdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.okhttpdemo.api.Api;
import com.bwie.okhttpdemo.api.UserApi;
import com.bwie.okhttpdemo.entity.UserInfoEntity;
import com.bwie.okhttpdemo.net.MyInterceptor;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        System.out.println(Thread.currentThread().getName());



    }

    /**
     * 初始化view
     */
    private void initView() {

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        System.out.println(constraintLayout.getChildCount());

//        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
//
//            View child = constraintLayout.getChildAt(i);//得到子view
//
//           if (i==1){
//               LinearLayout view = (LinearLayout) child;
//               view.getChildCount();
//           }
//
//
//        }

        constraintLayout.post(new Runnable() {
            @Override
            public void run() {

            }
        });








    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * 登录
     * @param view
     */
    public void login(View view) {
        //登录数据的校验
        verifyUserInfo();
        //校验成功后请求接口
        try {
            login("18612991023","111111");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     *
     * @param s 手机号
     * @param s1 密码
     */
    private void login(String s, String s1) throws IOException {

        HttpLoggingInterceptor httpLoggingInterceptor  = new HttpLoggingInterceptor();
        if (Api.isRelease){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }else{
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }


        //第一步，创建okhttpclient管理器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new MyInterceptor())//自定义应用拦截器
                .addNetworkInterceptor(new MyInterceptor())//自定义网络拦截器
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();//构建对象成功

//
//        RequestBody requestBody =RequestBody.create(MediaType.parse("application/x-www-from-urlencoded"),"2121212");
//

        //通过表单请求体进行请求，请求方式是post
        new MultipartBody.Builder();
        RequestBody requestBody = new FormBody.Builder().add("phone","18612991023")
                .add("pwd","111111")
                .build();
        //第二步，创建请求对象
        final Request request = new Request.Builder()
                .url(UserApi.LOGIN_URL)
                .post(requestBody)
                .build();

        //第三步，执行请求，返回一个回调对象
        //请求对象
        call = okHttpClient.newCall(request);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response response = call.execute();//同步请求，阻塞线程
//                    if (200==response.code() ){
//
//                    }else{
//
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        Call call2 = okHttpClient.newCall(request);
//        call2.execute();
        call.enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(Call call, IOException e) {

            }

            /**
             *
             * @param call call对象
             * @param response 响应对象
             * @throws IOException 异常
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (200==response.code() ){

                    System.out.println("threadname===="+Thread.currentThread().getName());;

                    String result = response.body().string();


                    System.out.println("result======"+result);
                    parseBean(result);
                }else{

                }


            }
        });
//        call2.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });




    }
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    /**
     * 解析json成bean对象
     * @param result
     */
    private void parseBean(String result) {



        final UserInfoEntity userInfoEntity = new Gson().fromJson(result,UserInfoEntity.class);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,userInfoEntity.message,Toast.LENGTH_SHORT).show();
                if (userInfoEntity!=null){
                    gotoMain();
                }
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,userInfoEntity.message,Toast.LENGTH_SHORT).show();
                if (userInfoEntity!=null){
                    gotoMain();
                }
            }
        });


    }

    /**
     * 跳抓
     */
    private void gotoMain() {
        startActivity(new Intent(this,MainActivity.class));

    }

    /**
     * 校验用户
     */
    private void verifyUserInfo() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call!=null){//逢对象必判空

            call.cancel();
            call = null;//置空，gc回收的时候
            System.gc();//


        }
    }
}
