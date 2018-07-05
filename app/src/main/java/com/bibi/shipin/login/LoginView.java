package com.bibi.shipin.login;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutLoginBinding;

/**
 * Created by zhangshexin on 2018/7/5.
 */

public class LoginView extends BaseView<LayoutLoginBinding,LoginViewModel>{
    @Override
    public int onCreateSetView() {
        return R.layout.layout_login;
    }

    @Override
    public LoginViewModel onCreateSetViewModel() {
        return new LoginViewModel(this);
    }
}
