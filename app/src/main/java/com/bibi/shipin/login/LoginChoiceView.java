package com.bibi.shipin.login;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutLoginChoiceBinding;

/**
 * Created by zhangshexin on 2018/7/19.
 * 选择是登录还是注册
 */

public class LoginChoiceView extends BaseView<LayoutLoginChoiceBinding,LoginChoiceViewModel>{
    @Override
    public int onCreateSetView() {
        return R.layout.layout_login_choice;
    }

    @Override
    public LoginChoiceViewModel onCreateSetViewModel() {
        return new LoginChoiceViewModel(this);
    }
}
