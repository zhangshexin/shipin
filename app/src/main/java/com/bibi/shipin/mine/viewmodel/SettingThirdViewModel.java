package com.bibi.shipin.mine.viewmodel;

import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.mine.SettingSecondView;
import com.bibi.shipin.mine.SettingThirdView;

/**
 * Created by zhangshexin on 2018/7/24.
 */

public class SettingThirdViewModel extends BaseViewModel implements View.OnClickListener {
    private SettingThirdView settingThirdView;
    public SettingThirdViewModel(SettingThirdView settingThirdView) {
        super();
        this.settingThirdView=settingThirdView;
        settingThirdView.getBinding().setSettingThirdPageOnClick(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
    }

    private void initView() {
        SettingSecondView.SettingSecond settingSecond=(SettingSecondView.SettingSecond)settingThirdView.getIntent().getSerializableExtra(SettingSecondView.EXTRA_TAG);
        settingThirdView.getBinding().settingThirdTextTitle.setText(settingSecond.getTitle());
        if(settingSecond.getCode()== SettingSecondView.SettingSecond.ACCOUNT.getCode()){
            settingThirdView.getBinding().settingThirdAccount.setVisibility(View.VISIBLE);
        }else if(settingSecond.getCode()== SettingSecondView.SettingSecond.MESSAGE_AT.getCode()){
            settingThirdView.getBinding().settingThirdMessageAt.setVisibility(View.VISIBLE);
        }
        else if(settingSecond.getCode()== SettingSecondView.SettingSecond.GENERAL_SIZE.getCode()){
            settingThirdView.getBinding().settingThirdGeneralSize.setVisibility(View.VISIBLE);
        }
        else if(settingSecond.getCode()== SettingSecondView.SettingSecond.GENERAL_WIFI.getCode()){
            settingThirdView.getBinding().settingThirdGeneralWifi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                settingThirdView.finish();
                break;
        }
    }
}
