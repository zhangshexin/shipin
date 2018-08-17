package com.bibi.shipin;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.bibi.shipin.databinding.LayoutSplashBinding;
import com.bibi.shipin.home.HomeView;

/**
 * Created by zhangshexin on 2018/8/17.
 */

public class ActivitySplash extends Activity {
    private LayoutSplashBinding binding;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent=new Intent(ActivitySplash.this, HomeView.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_splash);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessageDelayed(0, 3000);//延迟三秒
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(0);
    }

    @Override
    public void onBackPressed() {
        mHandler.removeMessages(0);
        super.onBackPressed();
    }
}
