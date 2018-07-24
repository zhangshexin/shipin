package com.bibi.shipin.mine;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutSettingThirdPageBinding;
import com.bibi.shipin.mine.viewmodel.SettingThirdViewModel;

/**
 * Created by zhangshexin on 2018/7/24.
 * 设置的三级页
 */

public class SettingThirdView extends BaseView<LayoutSettingThirdPageBinding,SettingThirdViewModel>{
    @Override
    public int onCreateSetView() {
        return R.layout.layout_setting_third_page;
    }

    @Override
    public SettingThirdViewModel onCreateSetViewModel() {
        return new SettingThirdViewModel(this);
    }
}
