package com.bibi.shipin;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;

import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.ActivityMainBinding;


/**
 * 主页
 */
public class MainView extends BaseView<ActivityMainBinding,MainViewModel> {

    @Override
    public void doOnCreateFunction() {
        super.doOnCreateFunction();

        //权限申请
        //读写sd卡
        //相机

    }

    @Override
    public int onCreateSetView() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel onCreateSetViewModel() {
        return new MainViewModel(this);
    }

    private int STORAGE_REQUEST_CODE = 100;

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 向用户解释为什么需要这个权限
            new AlertDialog.Builder(this)
                    .setMessage("下载视频，边播边存以及播放本地视频，需要以下权限。")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //申请权限
                            ActivityCompat.requestPermissions(MainView.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
                        }
                    })
                    .show();

        }
    }
}
