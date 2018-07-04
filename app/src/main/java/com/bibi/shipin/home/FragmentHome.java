package com.bibi.shipin.home;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseFragment;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.databinding.LayoutFragmentHomeBinding;
import com.bibi.shipin.home.viewmodel.FragmentHomeViewModel;

/**
 * 首页
 */

public class FragmentHome extends BaseFragment<LayoutFragmentHomeBinding, FragmentHomeViewModel> {

    @Override
    public int initContentView() {
        return R.layout.layout_fragment_home;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public FragmentHomeViewModel initViewModel() {
        FragmentHomeViewModel model=new FragmentHomeViewModel(this);
        setViewModel(model);
        return model;
    }

    //--------------------------------
    @Override
    public int onCreateSetView() {
        return 0;
    }

    @Override
    public BaseViewModel onCreateSetViewModel() {
        return null;
    }

    @Override
    public void doOnCreateFunction() {

    }
}