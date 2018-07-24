package com.bibi.shipin.mine.viewmodel;

import android.content.Intent;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.mine.SettingSecondView;
import com.bibi.shipin.mine.SettingThirdView;
import com.bibi.shipin.mine.SettingView;

/**
 * Created by zhangshexin on 2018/7/24.
 */

public class SettingSecondViewModel extends BaseViewModel implements View.OnClickListener {
    private SettingSecondView settingSecondView;

    public SettingSecondViewModel(SettingSecondView settingSecondView) {
        super();
        this.settingSecondView = settingSecondView;
        settingSecondView.getBinding().setSettingSecondPageOnClick(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
    }

    private Intent intent;
    /**
     * 初始view
     */
    private void initView() {
        intent=new Intent(settingSecondView, SettingThirdView.class);
        SettingView.Setting setting = (SettingView.Setting) settingSecondView.getIntent().getSerializableExtra(SettingView.EXTRA_TAG);
        settingSecondView.getBinding().settingSecondTextTitle.setText(setting.getTitle());
        if (setting.getCode() == SettingView.Setting.ACCOUNT.getCode()) {
            settingSecondView.getBinding().settingSecondAccount.setVisibility(View.VISIBLE);
        } else if (setting.getCode() == SettingView.Setting.GENERAL.getCode()) {
            settingSecondView.getBinding().settingSecondGeneral.setVisibility(View.VISIBLE);
        } else if (setting.getCode() == SettingView.Setting.MESSAGE.getCode()) {
            settingSecondView.getBinding().settingSecondMessage.setVisibility(View.VISIBLE);
        } else if (setting.getCode() == SettingView.Setting.PRIVACY.getCode()) {
            settingSecondView.getBinding().settingSecondPrivacy.setVisibility(View.VISIBLE);
        } else if (setting.getCode() == SettingView.Setting.SECURITY.getCode()) {
            settingSecondView.getBinding().settingSecondAccountSecurity.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finishAndSave();
                break;
            case R.id.setting_second_label_info:
                //去个人信息页
                intent.putExtra(SettingSecondView.EXTRA_TAG, SettingSecondView.SettingSecond.ACCOUNT);
                settingSecondView.startActivity(intent);
                break;
            case R.id.setting_second_label_modify:
                //去修改密码页
                break;
            case R.id.setting_second_label_logo:
                //查看登录记录
                break;
            case R.id.setting_second_label_wechat:
                //未绑定微信可以去绑定
                break;
            case R.id.setting_second_label_qq:
                //未绑定qq可以去绑定
                break;
            case R.id.setting_second_label_weibo:
                //未绑定微博可以去绑定
                break;
            case R.id.setting_second_label_mobile:
                //未绑定手机可以去绑定
                break;
            case R.id.setting_second_label_message_toggle:
                //接收推送通知
                break;
            case R.id.setting_second_label_message_atme:
                //@我的
                intent.putExtra(SettingSecondView.EXTRA_TAG, SettingSecondView.SettingSecond.MESSAGE_AT);
                settingSecondView.startActivity(intent);
                break;
            case R.id.setting_second_label_comment:
                //评论
                break;
            case R.id.setting_second_label_zan:
                //赞
                break;
            case R.id.setting_second_label_personal_push_toggle:
                //私信和推送
                break;
            case R.id.setting_second_privacy_label_permission_contact:
                //允许推荐通讯录好友
                break;
            case R.id.setting_second_privacy_label_permission_contact_search:
                //允许通过手机号搜到我
                break;
            case R.id.setting_second_general_label_player:
                //视频自动播放
                intent.putExtra(SettingSecondView.EXTRA_TAG, SettingSecondView.SettingSecond.GENERAL_WIFI);
                settingSecondView.startActivity(intent);
                break;
            case R.id.setting_second_general_label_size:
                //字号大小
                intent.putExtra(SettingSecondView.EXTRA_TAG, SettingSecondView.SettingSecond.GENERAL_SIZE);
                settingSecondView.startActivity(intent);
                break;
        }
    }

    /**
     * 退出并保存
     */
    private void finishAndSave() {
        settingSecondView.finish();
    }
}
