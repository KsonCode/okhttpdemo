package com.bwie.okhttpdemo.api;

/**
 * 总的网络api配置类
 */
public class Api {
    public static boolean  isRelease = false;//测试环境还是正式环境
    public static final String BASE_URL = isRelease?"http://mobile.bwstudent.com/small/":"http://172.17.8.100/small/";

    //用户


    //商品

    //资讯

    //订单
}
