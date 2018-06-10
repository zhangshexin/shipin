package com.bibi.shipin;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.SurfaceHolder;
import android.view.ViewGroup;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.ItemPlayerBinding;
import com.bibi.shipin.player.PlayerBean;
import com.bibi.shipin.util.Util;
import com.bumptech.glide.Glide;

/**
 * Created by zhangshexin on 2018/6/10.
 */

public class MainAdapter extends BaseAdapter<PlayerBean, BaseViewHolder> {
    private AliyunLocalSource mLocalSource = null;
    private AliyunVodPlayer aliyunVodPlayer;
    private MainView mainView;
    private int currentPosition;
    public MainAdapter(MainView context, AliyunVodPlayer aliyunVodPlayer) {
        super(context);
        mainView = context;
        this.aliyunVodPlayer = aliyunVodPlayer;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ItemPlayerBinding itemBinding = DataBindingUtil.inflate(inflater, R.layout.item_player, parent, false);
        return new BaseViewHolder(itemBinding);
    }

    public int getLayoutPosition() {
        return currentPosition;
    }

    public int getAdapterPosition() {
        return currentPosition;
    }

    @Override
    public void onBindVH(BaseViewHolder itemplayerBinding, int position) {
        currentPosition=position;
        PlayerBean bean = mList.get(position);
        ItemPlayerBinding binding = (ItemPlayerBinding) itemplayerBinding.getBinding();
        Glide.with(mainView).load(bean.getThumbUrl()).into(binding.playerThumbnail);
        //surface callback
//        binding.palyerSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                mainView.logI("surfaceCreated");
//                if(aliyunVodPlayer!=null)
//                    aliyunVodPlayer.setDisplay(holder);
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                mainView.logI("surfaceChanged");
//                if(aliyunVodPlayer!=null)
//                    aliyunVodPlayer.surfaceChanged();
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                mainView.logI( "surfaceDestroyed");
//            }
//        });

//        AliyunLocalSource.AliyunLocalSourceBuilder asb = new AliyunLocalSource.AliyunLocalSourceBuilder();
//        asb.setSource(bean.getUrl());
//        asb.setTitle(bean.getTitle());
//        mLocalSource=asb.build();
//        //处理视频播放
//        if (mLocalSource != null) {
//            aliyunVodPlayer.prepareAsync(mLocalSource);
//        }
    }
}
