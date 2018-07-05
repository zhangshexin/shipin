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
import com.bibi.shipin.databinding.LayoutFragmentMineBinding;
import com.bibi.shipin.home.viewmodel.FragmentMineViewModel;

/**
 * 我的
 */

public class FragmentMine extends BaseFragment<LayoutFragmentMineBinding,FragmentMineViewModel> {
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


    //---------------

    @Override
    public int initContentView() {
        return R.layout.layout_fragment_mine;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public FragmentMineViewModel initViewModel() {
        return new FragmentMineViewModel(this);
    }
}