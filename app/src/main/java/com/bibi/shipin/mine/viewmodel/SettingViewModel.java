package com.bibi.shipin.mine.viewmodel;

import android.content.Intent;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.mine.SettingSecondView;
import com.bibi.shipin.mine.SettingView;

/**
 * Created by zhangshexin on 2018/7/20.
 */

public class SettingViewModel extends BaseViewModel implements View.OnClickListener {
    private SettingView settingView;

    public SettingViewModel(SettingView settingView) {
        this.settingView = settingView;
        settingView.getBinding().setSettingOnClick(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(settingView, SettingSecondView.class);
        switch (v.getId()){
            case R.id.back_btn:
                settingView.finish();
                break;
            case R.id.setting_btn_clear:
                //清理缓存
                break;
            case R.id.setting_label_account:
                //账号设置
                intent.putExtra(SettingView.EXTRA_TAG, SettingView.Setting.ACCOUNT);
                settingView.startActivity(intent);
                break;
            case R.id.setting_label_account_ser:
                //账号安全设置
                intent.putExtra(SettingView.EXTRA_TAG, SettingView.Setting.SECURITY);
                settingView.startActivity(intent);
                break;
            case R.id.setting_label_message:
                //消息设置
                intent.putExtra(SettingView.EXTRA_TAG, SettingView.Setting.MESSAGE);
                settingView.startActivity(intent);
                break;
            case R.id.setting_label_general:
                //通用设置
                intent.putExtra(SettingView.EXTRA_TAG, SettingView.Setting.GENERAL);
                settingView.startActivity(intent);
                break;
            case R.id.setting_label_personal:
                //隐私设置
                intent.putExtra(SettingView.EXTRA_TAG, SettingView.Setting.PRIVACY);
                settingView.startActivity(intent);
                break;
            case R.id.setting_btn_login_out:
                //退出登录
                break;
        }
    }
}
