package com.bibi.shipin;

import android.app.Application;

import com.aliyun.common.httpfinal.QupaiHttpFinal;
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

        System.loadLibrary("live-openh264");//编码相关的库（必须load）
        System.loadLibrary("QuCore-ThirdParty");//SDK依赖的第三方库（必须load）
        System.loadLibrary("QuCore");//SDK核心库（必须load）


        QupaiHttpFinal.getInstance().initOkHttpFinal();
        com.aliyun.vod.common.httpfinal.QupaiHttpFinal.getInstance().initOkHttpFinal();
    }
}
