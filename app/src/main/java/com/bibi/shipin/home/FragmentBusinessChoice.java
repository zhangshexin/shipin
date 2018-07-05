package com.bibi.shipin.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseFragment;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.databinding.LayoutFragmentChoicenessListBinding;
import com.bibi.shipin.home.viewmodel.FragmentBusinessChoiceViewModel;

/**
 * 挖矿
 */

public class FragmentBusinessChoice extends BaseFragment<LayoutFragmentChoicenessListBinding,FragmentBusinessChoiceViewModel> {
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
        return R.layout.layout_fragment_choiceness_list;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public FragmentBusinessChoiceViewModel initViewModel() {
        return new FragmentBusinessChoiceViewModel(this);
    }
}