package com.bibi.shipin.player;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;

import java.lang.ref.WeakReference;

/**
 * Created by zhangshexin on 2018/6/26.
 */

public class VideoPlayerViewModel extends BaseViewModel implements View.OnClickListener {
    private String TAG = getClass().getName();
    private VideoPlayerView videoPlayerView;

    private AliyunVodPlayer aliyunVodPlayer;

    public VideoPlayerViewModel(VideoPlayerView videoPlayerView) {
        this.videoPlayerView = videoPlayerView;
        videoPlayerView.getBinding().setOnClick(this);

        initView();
    }

    private void initView() {

        aliyunVodPlayer = new AliyunVodPlayer(videoPlayerView);
        aliyunVodPlayer.setOnCircleStartListener(new MyCircleStartListener(this));
        aliyunVodPlayer.setOnPreparedListener(new MyPrepareListener(this));
        aliyunVodPlayer.setOnFirstFrameStartListener(new MyFirstFrameListener(this));
        aliyunVodPlayer.setOnErrorListener(new MyErrorListener(this));
        aliyunVodPlayer.setOnCompletionListener(new MyCompletionListener(this));
        aliyunVodPlayer.setOnSeekCompleteListener(new MySeekCompleteListener(this));
        aliyunVodPlayer.setOnStoppedListner(new MyStoppedListener(this));
        aliyunVodPlayer.setOnChangeQualityListener(new MyChangeQualityListener(this));
        aliyunVodPlayer.setVideoScalingMode(IAliyunVodPlayer.VideoScalingMode.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);


        SurfaceView surfaceView = new SurfaceView(videoPlayerView);
        videoPlayerView.getBinding().playerSurfaceParent.addView(surfaceView);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.e(TAG, "surfaceCreated: ===========");
                if (aliyunVodPlayer != null) {
                    aliyunVodPlayer.setDisplay(holder);
                    AliyunLocalSource.AliyunLocalSourceBuilder asb = new AliyunLocalSource.AliyunLocalSourceBuilder();
                    asb.setSource(videoPlayerView.getIntent().getStringExtra("outputPath"));
                    asb.setTitle("本地");
                    AliyunLocalSource mLocalSource = asb.build();
                    //处理视频播放
                    aliyunVodPlayer.prepareAsync(mLocalSource);

                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (aliyunVodPlayer != null)
                    aliyunVodPlayer.surfaceChanged();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.player_cancel:
                videoPlayerView.finish();
                break;
            case R.id.player_play:
                play();
                break;
            case R.id.player_stop:
                stop();
                break;
            case R.id.player_upload:
                upload();
                break;
        }
    }

    private void upload() {
        //上传到服务器

    }

    private void stop() {
        aliyunVodPlayer.pause();
        videoPlayerView.getBinding().playerPlay.setVisibility(View.VISIBLE);
        videoPlayerView.getBinding().playerStop.setVisibility(View.GONE);
        stopRefreshProgress();
    }

    private void play() {
        aliyunVodPlayer.start();
        videoPlayerView.getBinding().playerPlay.setVisibility(View.GONE);
        videoPlayerView.getBinding().playerStop.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestory() {
        stopRefreshProgress();
        if (aliyunVodPlayer != null) {
            aliyunVodPlayer.stop();
            aliyunVodPlayer.release();
        }
        super.onDestory();
    }

    ////////////////////////////ALI 回调监控//////////////////////////////////

    /**
     * 循环播放
     */
    private static class MyCircleStartListener implements IAliyunVodPlayer.OnCircleStartListener {
        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MyCircleStartListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }

        @Override
        public void onCircleStart() {
            VideoPlayerViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onCircleStart();
            }
        }
    }

    private void onCircleStart() {
        //循环播放处理
    }

    /**
     * 播放准备状态监听器
     */
    private static class MyPrepareListener implements IAliyunVodPlayer.OnPreparedListener {

        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MyPrepareListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }

        @Override
        public void onPrepared() {
            VideoPlayerViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onPrepared();
            }
        }
    }

    private void onPrepared() {
        //准备完成，可以开始播放
        aliyunVodPlayer.start();
    }

    /**
     * 首帧显示监听器
     */
    private static class MyFirstFrameListener implements IAliyunVodPlayer.OnFirstFrameStartListener {

        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MyFirstFrameListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }

        @Override
        public void onFirstFrameStart() {
            VideoPlayerViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onFirstFrameStart();
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                showProgress();
            }
        }
    };

    private void stopRefreshProgress() {
        mHandler.removeMessages(1);
    }

    private void onFirstFrameStart() {
        //首帧加载完成，开始显示进度
        showProgress();
    }

    private void showProgress() {
        int curPosition = (int) aliyunVodPlayer.getCurrentPosition();
        int duration = (int) aliyunVodPlayer.getDuration();
        int bufferPosition = aliyunVodPlayer.getBufferingPosition();
        videoPlayerView.getBinding().playerProgress.setMax(duration);
        videoPlayerView.getBinding().playerProgress.setSecondaryProgress(bufferPosition);
        videoPlayerView.getBinding().playerProgress.setProgress(curPosition);
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    /**
     * 播放器错误监听器
     */
    private static class MyErrorListener implements IAliyunVodPlayer.OnErrorListener {

        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MyErrorListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }

        @Override
        public void onError(int arg0, int arg1, String msg) {
            VideoPlayerViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onError(arg0, arg1, msg);
            }
        }
    }

    private void onError(int arg0, int arg1, String msg) {
        videoPlayerView.logE(msg);
    }

    /**
     * 播放完成监听器
     */
    private static class MyCompletionListener implements IAliyunVodPlayer.OnCompletionListener {
        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MyCompletionListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }

        @Override
        public void onCompletion() {
            VideoPlayerViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onCompletion();
            }
        }
    }

    private void onCompletion() {
        //播放结束
        //循环播放
        aliyunVodPlayer.replay();
    }

    /**
     * 快进状态监听器
     */
    private static class MySeekCompleteListener implements IAliyunVodPlayer.OnSeekCompleteListener {

        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MySeekCompleteListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }

        @Override
        public void onSeekComplete() {
            VideoPlayerViewModel activity = activityWeakReference.get();
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
        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MyStoppedListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }


        @Override
        public void onStopped() {
            VideoPlayerViewModel activity = activityWeakReference.get();
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

        private WeakReference<VideoPlayerViewModel> activityWeakReference;

        public MyChangeQualityListener(VideoPlayerViewModel skinActivity) {
            activityWeakReference = new WeakReference<VideoPlayerViewModel>(skinActivity);
        }

        @Override
        public void onChangeQualitySuccess(String finalQuality) {
            VideoPlayerViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onChangeQualitySuccess(finalQuality);
            }
        }

        @Override
        public void onChangeQualityFail(int code, String msg) {
            VideoPlayerViewModel activity = activityWeakReference.get();
            if (activity != null) {
                activity.onChangeQualityFail(code, msg);
            }
        }

    }

    private void onChangeQualitySuccess(String finalQuality) {
        videoPlayerView.logI("onChangeQualitySuccess");
    }


    private void onChangeQualityFail(int code, String msg) {
        videoPlayerView.logI("onChangeQualityFail。。。" + code + " ,  " + msg);
    }
}
