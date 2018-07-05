package com.bibi.shipin.home.business;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseFragment;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.databinding.LayoutFragmentListBinding;
import com.bibi.shipin.home.business.viewmodel.FragmentBusinessStartViewModel;

/**
 * Created by zhangshexin on 2018/7/5.
 * 创业
 */

public class FragmentBusinessStart extends BaseFragment<LayoutFragmentListBinding,FragmentBusinessStartViewModel>{
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

    @Override
    public int initContentView() {
        return R.layout.layout_fragment_list;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public FragmentBusinessStartViewModel initViewModel() {
        return new FragmentBusinessStartViewModel(this);
    }
}
