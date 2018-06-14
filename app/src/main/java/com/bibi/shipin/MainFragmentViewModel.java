package com.bibi.shipin;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.player.PlayerBean;
import com.bumptech.glide.Glide;

/**
 * Created by zhangshexin on 2018/6/11.
 */

public class MainFragmentViewModel extends BaseViewModel {
    private String TAG = getClass().getName();
    private MainFragment mainFragment;
    private PlayerBean bean;

    public MainFragmentViewModel(MainFragment mainFragment, PlayerBean bean) {
        super();
        this.mainFragment = mainFragment;
        this.bean = bean;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainFragment.getBinding().setMainFragmentVM(this);
        Glide.with(mainFragment).load(bean.getThumbUrl()).into(mainFragment.getBinding().playerThumbnail);
        mainFragment.getBinding().playerMsg.setText(bean.getTitle());

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttachFragment() {
        super.onAttachFragment();
    }

    public void prepareOk() {
        mainFragment.getBinding().playerThumbnail.setVisibility(View.GONE);
        mainFragment.getBinding().playerLoadingProgressBar.setVisibility(View.GONE);
    }

    public void removeSurface(){
        mainFragment.getBinding().playerSurfaceViewParent.removeAllViews();
    }
    /**
     * 滑动切换页面时需要切换数据
     */
    public void changeView() {
        removeSurface();
        SurfaceView surfaceView=new SurfaceView(mainFragment.getContext());
        mainFragment.getBinding().playerSurfaceViewParent.addView(surfaceView);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    Log.e(TAG, "surfaceCreated: ===========");
                    if (mainFragment.getAliyunVodPlayer() != null) {
                        mainFragment.getAliyunVodPlayer().setDisplay(holder);
                        AliyunLocalSource.AliyunLocalSourceBuilder asb = new AliyunLocalSource.AliyunLocalSourceBuilder();
                        asb.setSource(bean.getUrl());
                        asb.setTitle(bean.getTitle());
                        AliyunLocalSource mLocalSource = asb.build();
                        //处理视频播放
                        if (mainFragment.getAliyunVodPlayer() != null && mLocalSource != null) {
                            mainFragment.getAliyunVodPlayer().stop();
                            mainFragment.getAliyunVodPlayer().prepareAsync(mLocalSource);
                        }
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    if (mainFragment.getAliyunVodPlayer() != null)
                        mainFragment.getAliyunVodPlayer().surfaceChanged();
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                }
            });
    }
}
