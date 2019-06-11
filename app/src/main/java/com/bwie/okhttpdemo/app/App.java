package com.bwie.okhttpdemo.app;

import android.app.Application;

/**
 * 对象存活的时间，加载到内存里，时间非常长，伴随着当前app的产生和消亡（进程杀死）
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initZxing();
        initMap();


        //
        //
        //
        //



    }

    /**
     *
     */
    private void initMap() {
    }

    /**
     *
     */
    private void initZxing() {
//        ZXingLibrary.initDisplayOpinion(this);
    }
}
