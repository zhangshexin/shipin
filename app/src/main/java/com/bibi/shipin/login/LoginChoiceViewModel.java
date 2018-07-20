package com.bibi.shipin.login;

import android.content.Intent;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;

/**
 * Created by zhangshexin on 2018/7/19.
 */

public class LoginChoiceViewModel extends BaseViewModel implements View.OnClickListener{
    private LoginChoiceView loginChoiceView;
    public LoginChoiceViewModel(LoginChoiceView loginChoiceView) {
        super();
        this.loginChoiceView=loginChoiceView;
        loginChoiceView.getBinding().setLoginChoickClick(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                loginChoiceView.finish();
                break;
            case R.id.login_choice_btn_login:
                //去登录
                loginChoiceView.startActivity(new Intent(loginChoiceView,LoginView.class));
                break;
            case R.id.login_choice_btn_register:
                //去注册
                break;
        }
    }
}
