package com.bibi.shipin.mine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.LayoutMyFansAttentionsItemBinding;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.mine.MyFansAndAttentionsView;

/**
 * Created by zhangshexin on 2018/7/12.
 */

public class MyFansAndAttentionsAdapter extends BaseAdapter<WorkBean, BaseViewHolder> {
    /**
     * 我的粉丝
     */
    public static final int TAG_FANS=1;
    /**
     * 我的关注
     */
    public static final int TAG_ATTENTIONS=2;

    private int tag;
    public MyFansAndAttentionsAdapter(MyFansAndAttentionsView context,int tag) {
        super(context);
        this.tag=tag;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        LayoutMyFansAttentionsItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.layout_my_fans_attentions_item,parent,false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        LayoutMyFansAttentionsItemBinding binding=(LayoutMyFansAttentionsItemBinding) baseViewHolder.getBinding();
        switch (tag){
            case TAG_ATTENTIONS:
                binding.myFansAttentionsBtn.setVisibility(View.GONE);
                break;
            case TAG_FANS:
                break;
        }
    }
}
