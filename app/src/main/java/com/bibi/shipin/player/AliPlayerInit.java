package com.bibi.shipin.player;

import android.content.Context;
import android.os.Environment;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.VcPlayerLog;
import com.aliyun.vodplayer.downloader.AliyunDownloadConfig;
import com.aliyun.vodplayer.downloader.AliyunDownloadManager;

/**
 * Created by zhangshexin on 2018/6/10.
 *
 * 作一些初始化的工作
 */

public class AliPlayerInit {
    public static void aliPlayerInit(Context context) {
        //初始化播放器（只需调用一次即可，建议在application中初始化）
        AliVcMediaPlayer.init(context);
    }
    public static void aliPlayerLog(boolean log){
        if(log)
            VcPlayerLog.enableLog();
    }

    public static void initConfig(Context context){
        //设置保存密码。此密码如果更换，则之前保存的视频无法播放
        AliyunDownloadConfig config = new AliyunDownloadConfig();
        config.setSecretImagePath(Environment.getExternalStorageDirectory().getAbsolutePath()+"/aliyun/encryptedApp.dat");
        //设置保存路径。请确保有SD卡访问权限。
        config.setDownloadDir(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test_save/");
        //设置同时下载个数
        config.setMaxNums(2);

        AliyunDownloadManager.getInstance(context).setDownloadConfig(config);
    }
}
