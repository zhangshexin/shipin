package com.bibi.shipin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;

import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.player.PlayerBean;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshexin on 2018/6/8.
 */

public class MainViewModel extends BaseViewModel {
    private MainView mainView;
    private MainFragmentAdapter adapter;
    private AliyunVodPlayer aliyunVodPlayer;
    private List<MainFragment> pages=new ArrayList<>();
    private List<PlayerBean> beans=new ArrayList<>();

    public MainViewModel(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * 初始化播放器
     */
    private void initPlayer(){
        aliyunVodPlayer = new AliyunVodPlayer(mainView);
        aliyunVodPlayer.setOnCircleStartListener(new MyCircleStartListener(this));
        aliyunVodPlayer.setOnPreparedListener(new MyPrepareListener(this));
        aliyunVodPlayer.setOnFirstFrameStartListener(new MyFirstFrameListener(this));
        aliyunVodPlayer.setOnErrorListener(new MyErrorListener(this));
        aliyunVodPlayer.setOnCompletionListener(new MyCompletionListener(this));
        aliyunVodPlayer.setOnSeekCompleteListener(new MySeekCompleteListener(this));
        aliyunVodPlayer.setOnStoppedListner(new MyStoppedListener(this));
        aliyunVodPlayer.setOnChangeQualityListener(new MyChangeQualityListener(this));
        aliyunVodPlayer.setVideoScalingMode(IAliyunVodPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
        //监权，可以先不加
//        aliyunVodPlayer.setReferer("");
        aliyunVodPlayer.enableNativeLog();
    }
    private int currentPosition=0;
    @Override
    public void onCreate() {
        super.onCreate();
        initPlayer();
        initData();




        adapter = new MainFragmentAdapter(mainView.getSupportFragmentManager(),pages);
        mainView.getBinding().videoListView.setOffscreenPageLimit(0);
        mainView.getBinding().videoListView.setAdapter(adapter);
        mainView.getBinding().videoListView.setCurrentItem(0);
        mainView.getBinding().videoListView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mainView.logE("onPageScrolled: "+position);
            }

            @Override
            public void onPageSelected(int position) {
                stopRefreshProgress();
                currentPosition=position;
                mainView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.getItem(currentPosition).getViewModel().changeView();
                    }
                });
                mainView.logE("   选中的页   "+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        mainView.getBinding().videoListView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                pages.get(0).getViewModel().changeView();
                mainView.getBinding().videoListView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }
    private void initData(){

            PlayerBean bean1=new PlayerBean();
            bean1.setThumbUrl("https://p1.pstatp.com/large/8813000883a6d59bffb4.jpg");
            bean1.setTitle("玩命的职业【抖音短视频】");
            bean1.setUrl("http://player.alicdn.com/video/aliyunmedia.mp4");
            beans.add(bean1);

        PlayerBean bean2=new PlayerBean();
        bean2.setThumbUrl("https://p3.pstatp.com/large/8cb3000263c43fa87d4c.jpg");
        bean2.setTitle("因为生活的不好，父母早上给我带来二箱奶，心里说不出来的难受【抖音短视频】");
        bean2.setUrl("http://livetest.aliyunlive.com/9725750fa11648eba3426f65920ccecd/bce6da0b4afd43bb9fd06af269816b82-4b6ffae84f2e1d243955ecaedcf11a3e.m3u8");
        beans.add(bean2);

        PlayerBean bean3=new PlayerBean();
        bean3.setThumbUrl("https://p3.pstatp.com/large/8c7f000eb468687f8fc2.jpg");
        bean3.setTitle("你知道你为什么自卑吗？【抖音短视频】");
        bean3.setUrl("http://livetest.aliyunlive.com/af6b16749fb44f2cb26c7c9b7cf3e3b2/3d8d93f8791c4e019388fd57e7dc9efb-4b6ffae84f2e1d243955ecaedcf11a3e.m3u8");
        beans.add(bean3);

        PlayerBean bean4=new PlayerBean();
        bean4.setThumbUrl("https://p3.pstatp.com/large/8cb7000a22518f66d104.jpg");
        bean4.setTitle("曹德旺的创业故事【抖音短视频】");
        bean4.setUrl("http://saas-video-qp.qupaicloud.com/299B3F9B-15BE76DF272-1767-9096-266-17559/8b8c03c80c2f43bf903a76621a6008d8.m3u8");
        beans.add(bean4);

            for (PlayerBean pb:beans){
                MainFragment fragment=new MainFragment();
                Bundle data=new Bundle();
                data.putSerializable("bean",pb);
                fragment.setArguments(data);
                fragment.setAliyunVodPlayer(aliyunVodPlayer);
                pages.add(fragment);
            }

    }

//    @Override
//    public void onResume() {
//        if(aliyunVodPlayer!=null) {
//            aliyunVodPlayer.resume();
//        }
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        if(aliyunVodPlayer!=null) {
//            aliyunVodPlayer.pause();
//        }
//        super.onPause();
//    }

    @Override
    public void onDestory() {
        if(aliyunVodPlayer!=null) {
            aliyunVodPlayer.stop();
            aliyunVodPlayer.release();
        }
        stopRefreshProgress();
        super.onDestory();
    }


    ////////////////////////////ALI 回调监控//////////////////////////////////

    /**
     * 循环播放
     */
    private static class MyCircleStartListener implements IAliyunVodPlayer.OnCircleStartListener {
        private WeakReference<MainViewModel> activityWeakReference;

        public MyCircleStartListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }

        @Override
        public void onCircleStart() {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onCircleStart();
            }
        }
    }
    private void onCircleStart(){
        //循环播放处理
    }

    /**
     * 播放准备状态监听器
     */
    private static class MyPrepareListener implements IAliyunVodPlayer.OnPreparedListener {

        private WeakReference<MainViewModel> activityWeakReference;

        public MyPrepareListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }

        @Override
        public void onPrepared() {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onPrepared();
            }
        }
    }
    private void onPrepared(){
        //准备完成，可以开始播放
        aliyunVodPlayer.start();
        pages.get(currentPosition).getViewModel().prepareOk();
    }

    /**
     * 首帧显示监听器
     */
    private static class MyFirstFrameListener implements IAliyunVodPlayer.OnFirstFrameStartListener {

        private WeakReference<MainViewModel> activityWeakReference;

        public MyFirstFrameListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }

        @Override
        public void onFirstFrameStart() {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onFirstFrameStart();
            }
        }
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                showProgress();
            }
        }
    };
    private void stopRefreshProgress(){
        mHandler.removeMessages(1);
    }
    private void onFirstFrameStart(){
        //首帧加载完成，开始显示进度
        showProgress();
        mHandler.sendEmptyMessageDelayed(1,1000);
    }
    private void showProgress(){
        int curPosition = (int) aliyunVodPlayer.getCurrentPosition();
        int duration = (int) aliyunVodPlayer.getDuration();
        int bufferPosition = aliyunVodPlayer.getBufferingPosition();
        mainView.getBinding().playerProgress.setMax(duration);
        mainView.getBinding().playerProgress.setSecondaryProgress(bufferPosition);
        mainView.getBinding().playerProgress.setProgress(curPosition);
    }
    /**
     * 播放器错误监听器
     */
    private static class MyErrorListener implements IAliyunVodPlayer.OnErrorListener {

        private WeakReference<MainViewModel> activityWeakReference;

        public MyErrorListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }

        @Override
        public void onError(int arg0, int arg1, String msg) {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onError(arg0, arg1, msg);
            }
        }
    }

    private void onError(int arg0, int arg1, String msg){
        mainView.logE(msg);
    }

    /**
     * 播放完成监听器
     */
    private static class MyCompletionListener implements IAliyunVodPlayer.OnCompletionListener {
        private WeakReference<MainViewModel> activityWeakReference;

        public MyCompletionListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }

        @Override
        public void onCompletion() {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onCompletion();
            }
        }
    }
    private void onCompletion(){
        //播放结束
        //循环播放
        aliyunVodPlayer.replay();
    }

    /**
     * 快进状态监听器
     */
    private static class MySeekCompleteListener implements IAliyunVodPlayer.OnSeekCompleteListener {

        private WeakReference<MainViewModel> activityWeakReference;

        public MySeekCompleteListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }

        @Override
        public void onSeekComplete() {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onSeekComplete();
            }
        }
    }

    private void onSeekComplete() {
       //快进完成
    }

    /**
     * 停止监听器
     */
    private static class MyStoppedListener implements IAliyunVodPlayer.OnStoppedListener {
        private WeakReference<MainViewModel> activityWeakReference;

        public MyStoppedListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }


        @Override
        public void onStopped() {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onStopped();
            }
        }
    }

    private void onStopped() {

    }

    /**
     * 切换播放值量监听器
     */
    private static class MyChangeQualityListener implements IAliyunVodPlayer.OnChangeQualityListener {

        private WeakReference<MainViewModel> activityWeakReference;

        public MyChangeQualityListener(MainViewModel skinActivity) {
            activityWeakReference = new WeakReference<MainViewModel>(skinActivity);
        }

        @Override
        public void onChangeQualitySuccess(String finalQuality) {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onChangeQualitySuccess(finalQuality);
            }
        }

        @Override
        public void onChangeQualityFail(int code, String msg) {
            MainViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onChangeQualityFail(code, msg);
            }
        }

    }

    private void onChangeQualitySuccess(String finalQuality) {
        mainView.logI( "onChangeQualitySuccess");
    }


    private void onChangeQualityFail(int code, String msg) {
        mainView.logI("onChangeQualityFail。。。" + code + " ,  " + msg);
    }
}
