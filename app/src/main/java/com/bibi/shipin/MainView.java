package com.bibi.shipin;

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
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
}
