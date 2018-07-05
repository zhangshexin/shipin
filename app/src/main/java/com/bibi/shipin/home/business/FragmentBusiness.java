package com.bibi.shipin.home.business;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseFragment;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.databinding.LayoutFragmentListBinding;
import com.bibi.shipin.home.business.viewmodel.FragmentBusinessViewModel;

/**
 * Created by zhangshexin on 2018/7/5.
 * 商业
 */

public class FragmentBusiness extends BaseFragment<LayoutFragmentListBinding,FragmentBusinessViewModel> {
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
    public FragmentBusinessViewModel initViewModel() {
        return new FragmentBusinessViewModel(this);
    }
}
