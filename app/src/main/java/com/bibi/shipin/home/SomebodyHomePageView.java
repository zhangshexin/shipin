package com.bibi.shipin.home;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutSomebodyHomepageBinding;

/**
 * Created by zhangshexin on 2018/7/12.
 *
 * 某人的主页
 */

public class SomebodyHomePageView extends BaseView<LayoutSomebodyHomepageBinding,SomebodyHomePageViewModel>{
    @Override
    public int onCreateSetView() {
        return R.layout.layout_somebody_homepage;
    }

    @Override
    public SomebodyHomePageViewModel onCreateSetViewModel() {
        return new SomebodyHomePageViewModel(this);
    }
}
