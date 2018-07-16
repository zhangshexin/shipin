package com.bibi.shipin.home.viewmodel.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.IncludeLayoutCommentItemBinding;
import com.bibi.shipin.home.beans.WorkBean;

/**
 * Created by zhangshexin on 2018/7/16.
 * 评论列表
 */

public class CommentAdapter extends BaseAdapter<WorkBean, BaseViewHolder>{

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        IncludeLayoutCommentItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.include_layout_comment_item,parent,false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {

    }
}
