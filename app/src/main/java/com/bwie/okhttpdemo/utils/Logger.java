package com.bwie.okhttpdemo.utils;

import android.util.Log;

import com.bwie.okhttpdemo.api.Api;

public class Logger {

    public static void d(String msg){
        if (!Api.isRelease){
            Log.d(Logger.class.getSimpleName(),msg);
        }

    }
    public static void e(String msg){
        if (!Api.isRelease){
            Log.e(Logger.class.getSimpleName(),msg);
        }

    }
}
