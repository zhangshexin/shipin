package com.bibi.shipin.home;

/**
 * Created by zhangshexin on 2018/7/2.
 */

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayouyHomeBinding;

public class HomeView extends BaseView<LayouyHomeBinding,HomeViewModel> {


    @Override
    public int onCreateSetView() {
        return R.layout.layouy_home;
    }

    @Override
    public HomeViewModel onCreateSetViewModel() {
        return new HomeViewModel(this);
    }
}
