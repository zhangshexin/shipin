package com.bibi.shipin.base.impl;

import android.support.v4.app.Fragment;

/**
 * Created by zhangshexin on 2018/6/8.
 */

public interface IBaseViewModel {
    public void onCreate();
    public void onResume();
    public void onPause();
    public void onDestory();

    public void onAttachFragment();
}
