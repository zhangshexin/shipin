package com.bibi.shipin;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private MainAdapter adapter;
    private AliyunVodPlayer aliyunVodPlayer;

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
        aliyunVodPlayer.setReferer("http://aliyun.com");
        aliyunVodPlayer.enableNativeLog();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        adapter = new MainAdapter(mainView,aliyunVodPlayer);
        LinearLayoutManager llm = new LinearLayoutManager(mainView);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mainView.getBinding().videoListView.setLayoutManager(llm);
        mainView.getBinding().videoListView.setAdapter(adapter);
        mainView.getBinding().videoListView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
//        mainView.getBinding().videoListView.setOnFlingListener(new RecyclerView.OnFlingListener() {
//            @Override
//            public boolean onFling(int velocityX, int velocityY) {
//                int currentPosition=adapter.getLayoutPosition();
//                mainView.logE("velocityY:"+velocityY+"---------------velocityX:"+velocityX);
//                if(currentPosition!=-1)
//                    if(10000<Math.abs(velocityX)){
//                        if(velocityX<0&&currentPosition!=0){
//                            //向上
//                            mainView.getBinding().videoListView.smoothScrollToPosition(currentPosition-1);
//                        }else if(velocityX>0&&currentPosition!=adapter.mList.size()-1){
//                            //向下
//                            mainView.getBinding().videoListView.smoothScrollToPosition(currentPosition+1);
//                        }
//                    }
//                return true;
//            }
//        });
        List<PlayerBean> beans=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PlayerBean bean=new PlayerBean();
            bean.setThumbUrl("https://img-blog.csdn.net/20170423125838469?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSnNhZ2FjaXR5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");
            bean.setTitle("lala");
            bean.setUrl("http://player.alicdn.com/video/aliyunmedia.mp4");
            beans.add(bean);
        }
        adapter.loadMoreData(beans);
        initPlayer();
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
    private void onFirstFrameStart(){
        //首帧加载完成，开始显示进度
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
