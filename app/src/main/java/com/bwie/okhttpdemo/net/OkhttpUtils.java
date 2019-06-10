package com.bwie.okhttpdemo.net;

import android.os.Handler;

import com.bwie.okhttpdemo.api.Api;
import com.bwie.okhttpdemo.utils.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 网络请求工具类
 */
public class OkhttpUtils {

    private static volatile OkhttpUtils mInstance;
    private final OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    /**
     * 私有构造函数
     */
    private OkhttpUtils(){


        HttpLoggingInterceptor httpLoggingInterceptor  = new HttpLoggingInterceptor();
        if (Api.isRelease){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }else{
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        //自定义应用拦截器
//自定义网络拦截器
//构建对象成功
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new MyInterceptor())//自定义应用拦截器
                .addNetworkInterceptor(new MyInterceptor())//自定义网络拦截器
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();


    }

    /**
     * 双重检验锁机制
     * @return
     */
    public static OkhttpUtils getInstance(){
        if (mInstance==null){//判空，防止多次创建对象,只适合单线程访问
            synchronized (OkhttpUtils.class){//防止多线程并发导致的数据安全问题
                if (mInstance==null){//防止多个线程，创建多个实例
                    mInstance = new OkhttpUtils();//自己创建自己的对象
                }
            }
        }
        return mInstance;
    }


    /**
     * get请求
     */
    public void doGet(String url,NetCallback netCallback){

        Request request = new Request.Builder()
                .get()
                .url(url)
//                .header("userId","")
//                .header("sessionId","")
                .build();
        callData(request,netCallback);

    }

    /**
     * post请求
     */
    public void doPost(String url, HashMap<String,String> params,NetCallback netCallback){

        FormBody.Builder builder = new FormBody.Builder();
        if (params!=null&&params.size()>0){
            for (Map.Entry<String, String> param : params.entrySet()) {
                String key = param.getKey();//得到key
                String value = param.getValue();//得到值

                builder.add(key,value);//添加请求体，form的表单的数据，以key和value形式添加

            }
        }

        Request request = new Request.Builder()
                .get()
                .url(url)
                .post(builder.build())//请求体对象
//                .header("userId","")
//                .header("sessionId","")
                .build();
        callData(request,netCallback);

    }

    /**
     * 请求数据
     * @param request
     */
    private void callData(Request request, final NetCallback netCallback) {
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.e(e.toString());
                        if (netCallback != null) {
                            netCallback.failure("网络可能有问题，请稍后再试");
                        }

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (200 == response.code()) {
                            final String result = response.body().string();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    if (netCallback != null) {
                                        netCallback.success(result);
                                    }

                                }
                            });
                        }

                    }
                });
    }







}
