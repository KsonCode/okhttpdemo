package com.bwie.okhttpdemo.net;

import com.bwie.okhttpdemo.utils.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();//得到请求对象

        Logger.e("--> "+request.method()+" "+request.url()+" ");


        Response response = chain.proceed(request);//得到响应对象

        return response;
    }
}
