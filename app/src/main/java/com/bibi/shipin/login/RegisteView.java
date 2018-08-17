package com.bibi.shipin.login;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutRegisteBinding;

/**
 * Created by zhangshexin on 2018/7/31.
 * 注册页
 */

public class RegisteView extends BaseView<LayoutRegisteBinding,RegisteViewModel>{

    @Override
    public int onCreateSetView() {
        return R.layout.layout_registe;
    }

    @Override
    public RegisteViewModel onCreateSetViewModel() {
        return new RegisteViewModel(this);
    }
}
