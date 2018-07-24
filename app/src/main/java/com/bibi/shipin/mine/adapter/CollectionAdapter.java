package com.bibi.shipin.mine.adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.LayoutCollectionItemBinding;
import com.bibi.shipin.databinding.LayoutFragmentBusinessItemBinding;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.mine.MyCollectionView;
import com.bibi.shipin.player.VideoPlayerView;

/**
 * Created by zhangshexin on 2018/7/12.
 */

public class CollectionAdapter extends BaseAdapter<WorkBean, BaseViewHolder> {
private String TAG=CollectionAdapter.class.getName();
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
        LayoutCollectionItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_collection_item, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        LayoutCollectionItemBinding binding = (LayoutCollectionItemBinding) baseViewHolder.getBinding();
        binding.setCollectionAdapter(this);
        binding.setPosition(position);
    }

    public void player(int position) {
        Log.e(TAG, "player: ###########" + position);
        Intent intent=new Intent(fragmentHome, VideoPlayerView.class);
        fragmentHome.startActivity(intent);
    }

    public void clickLeft(int position){

    }
    public void clickRight(int position){}
}
