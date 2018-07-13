package com.bibi.shipin.mine.adapter;

import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.LayoutFragmentBusinessItemBinding;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.mine.MyCollectionView;

/**
 * Created by zhangshexin on 2018/7/12.
 */

public class CollectionAdapter extends BaseAdapter<WorkBean, BaseViewHolder> {

    /**
     * 我的收藏
     */
    public static final int TAG_MY_ATTENTION = 0;


    private MyCollectionView fragmentHome;
    private int tag;

    public CollectionAdapter(MyCollectionView context, int tag) {
        super(context);
        this.fragmentHome = context;
        this.tag = tag;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        LayoutFragmentBusinessItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_business_item, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        LayoutFragmentBusinessItemBinding binding = (LayoutFragmentBusinessItemBinding) baseViewHolder.getBinding();
        switch (tag) {
            case TAG_MY_ATTENTION:
                binding.fragmentChoicenessItemLabelLeft.setText(R.string.collection);
                binding.fragmentChoicenessItemLabelRight.setText(R.string.submit);
                break;
        }
    }
}
