package com.bibi.shipin.home.business.adapter;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
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

public class BusinessListAdapter extends BaseAdapter<WorkBean, BaseViewHolder> implements View.OnClickListener {

    private String TAG = BusinessListAdapter.class.getName();
    /**
     * 新知
     */
    public static final int TAG_NEW = 1;
    /**
     * 商业
     */
    public static final int TAG_BUSINESS = 2;

    private FragmentBusiness fragmentHome;
    private int tag;

    public BusinessListAdapter(FragmentBusiness context, int tag) {
        super(context.getContext());
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
        binding.setBusinessAdapter(this);
        binding.setBusinessItemOnClick(this);
        binding.setPosition(position);
        switch (tag) {
            case TAG_BUSINESS:
                binding.fragmentChoicenessItemLabelRight.setText(R.string.contract);
                break;
            case TAG_NEW:
                binding.fragmentChoicenessItemLabelRight.setText(R.string.download);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_choiceness_item_label_left:
                break;
            case R.id.fragment_choiceness_item_label_right:
                break;
        }
    }

    public void player(int position) {
        Log.e(TAG, "player: +++++++++++" + position);
    }
}
