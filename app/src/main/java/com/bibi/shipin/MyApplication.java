package com.bibi.shipin;

import android.app.Application;

import com.bibi.shipin.player.AliPlayerInit;

/**
 * Created by zhangshexin on 2018/6/10.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化阿里播放器
        AliPlayerInit.aliPlayerInit(getApplicationContext());
        AliPlayerInit.aliPlayerLog(true);
        AliPlayerInit.initConfig(getApplicationContext());
    }
}
