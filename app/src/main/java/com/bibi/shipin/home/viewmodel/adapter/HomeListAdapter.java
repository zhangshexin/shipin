package com.bibi.shipin.home.viewmodel.adapter;

import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.LayoutFragmentHomeListItemBinding;
import com.bibi.shipin.home.FragmentHome;
import com.bibi.shipin.home.beans.WorkBean;

/**
 * Created by zhangshexin on 2018/7/4.
 */

public class HomeListAdapter extends BaseAdapter<WorkBean,BaseViewHolder> {

    private FragmentHome fragmentHome;
    public HomeListAdapter(FragmentHome context) {
        super(context.getContext());
        this.fragmentHome=context;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        LayoutFragmentHomeListItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.layout_fragment_home_list_item,parent,false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {

    }
}
