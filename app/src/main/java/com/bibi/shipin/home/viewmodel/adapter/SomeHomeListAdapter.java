package com.bibi.shipin.home.viewmodel.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.LayoutSomebodyHomepageItemBinding;
import com.bibi.shipin.home.SomebodyHomePageView;
import com.bibi.shipin.home.beans.WorkBean;

/**
 * Created by zhangshexin on 2018/7/12.
 * 某人主页适配器
 */

public class SomeHomeListAdapter extends BaseAdapter<WorkBean, BaseViewHolder>{
    private SomebodyHomePageView context;

    public SomeHomeListAdapter(SomebodyHomePageView context) {
        super(context);
        this.context=context;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        LayoutSomebodyHomepageItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.layout_somebody_homepage_item,parent,false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        LayoutSomebodyHomepageItemBinding binding=(LayoutSomebodyHomepageItemBinding)baseViewHolder.getBinding();

    }
}
