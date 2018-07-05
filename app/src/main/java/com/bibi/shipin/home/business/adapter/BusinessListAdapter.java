package com.bibi.shipin.home.business.adapter;

import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.LayoutFragmentBusinessItemBinding;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.home.business.FragmentBusiness;

/**
 * Created by zhangshexin on 2018/7/4.
 */

public class BusinessListAdapter extends BaseAdapter<WorkBean, BaseViewHolder> {

    private FragmentBusiness fragmentHome;
    private boolean isNew;

    public BusinessListAdapter(FragmentBusiness context, boolean isNew) {
        super(context.getContext());
        this.fragmentHome = context;
        this.isNew = isNew;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        LayoutFragmentBusinessItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_business_item, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        LayoutFragmentBusinessItemBinding binding = (LayoutFragmentBusinessItemBinding) baseViewHolder.getBinding();
        if (isNew) {
            binding.fragmentChoicenessItemLabelRight.setText(R.string.download);
        } else {
            binding.fragmentChoicenessItemLabelRight.setText(R.string.contract);
        }
    }
}
