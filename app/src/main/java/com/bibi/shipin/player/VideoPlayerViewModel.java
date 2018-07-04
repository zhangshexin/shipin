package com.bibi.shipin.player;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.alibaba.sdk.android.vod.upload.VODSVideoUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODSVideoUploadClient;
import com.alibaba.sdk.android.vod.upload.VODSVideoUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.SvideoInfo;
import com.alibaba.sdk.android.vod.upload.session.VodHttpClientConfig;
import com.alibaba.sdk.android.vod.upload.session.VodSessionCreateInfo;
import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangshexin on 2018/6/26.
 */

public class VideoPlayerViewModel extends BaseViewModel implements View.OnClickListener {
    private String TAG = getClass().getName();
    private VideoPlayerView videoPlayerView;

    private AliyunVodPlayer aliyunVodPlayer;

    private VODSVideoUploadClient vodsVideoUploadClient;


    //------------------上传中使用-----------------------
    private long progress=0;

    public VideoPlayerViewModel(VideoPlayerView videoPlayerView) {
        this.videoPlayerView = videoPlayerView;
        videoPlayerView.getBinding().setOnClick(this);

        initView();
        initUpload();
    }

    private void initUpload() {
        //1.初始化短视频上传对象
        vodsVideoUploadClient = new VODSVideoUploadClientImpl(videoPlayerView);
        vodsVideoUploadClient.init();
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
    //获取sts的url
    private String url = "";
    private OkHttpClient client;

    private void upload() {
        if(videoPlayerView.getBinding().videoUploadStatus.getVisibility()==View.VISIBLE)
            upCancel();
        else
            upStart();

    }

    /**
     * 生成上传要用到的参数
     */
    private void genParm(String imagePath, String videoPath, String accessKeyId, String accessKeySecret, String securityToken, String expriedTime, String requestID) {
        //参数请确保存在，如不存在SDK内部将会直接将错误throw Exception
        // 文件路径保证存在之外因为Android 6.0之后需要动态获取权限，请开发者自行实现获取"文件读写权限".
        VodHttpClientConfig vodHttpClientConfig = new VodHttpClientConfig.Builder()
                .setMaxRetryCount(2)//重试次数
                .setConnectionTimeout(15 * 1000)//连接超时
                .setSocketTimeout(15 * 1000)//socket超时
                .build();
        //构建短视频VideoInfo,常见的描述，标题，详情都可以设置
        SvideoInfo svideoInfo = new SvideoInfo();
        svideoInfo.setTitle("这是一个测试短视频");
        svideoInfo.setDesc("这是一个测试短视频");
        svideoInfo.setCateId(1);
        //构建点播上传参数(重要)
        VodSessionCreateInfo vodSessionCreateInfo = new VodSessionCreateInfo.Builder()
                .setImagePath(imagePath)//图片地址
                .setVideoPath(videoPath)//视频地址
                .setAccessKeyId(accessKeyId)//临时accessKeyId
                .setAccessKeySecret(accessKeySecret)//临时accessKeySecret
                .setSecurityToken(securityToken)//securityToken
                .setExpriedTime(expriedTime)//STStoken过期时间
                .setRequestID(requestID)//requestID，开发者可以传将获取STS返回的requestID设置也可以不设.
                .setIsTranscode(false)//是否转码.如开启转码请AppSever务必监听服务端转码成功的通知
                .setSvideoInfo(svideoInfo)//短视频视频信息
                .setVodHttpClientConfig(vodHttpClientConfig)//网络参数
                .build();
        vodsVideoUploadClient.uploadWithVideoAndImg(vodSessionCreateInfo, new VODSVideoUploadCallback() {
            @Override
            public void onUploadSucceed(String videoId, String imageUrl) {
                //上传成功返回视频ID和图片URL.
                Log.d(TAG, "onUploadSucceed" + "videoId:" + videoId + "imageUrl" + imageUrl);
                upFailed(false);
            }

            @Override
            public void onUploadFailed(String code, String message) {
                //上传失败返回错误码和message.错误码有详细的错误信息请开发者仔细阅读
                Log.d(TAG, "onUploadFailed" + "code" + code + "message" + message);
                upFailed(true);
            }

            @Override
            public void onUploadProgress(long uploadedSize, long totalSize) {
                //上传的进度回调,非UI线程
                Log.d(TAG, "onUploadProgress" + uploadedSize * 100 / totalSize);
                progress = uploadedSize * 100 / totalSize;
                mHandler.sendEmptyMessage(WHAT_PROGRESS);
            }

            @Override
            public void onSTSTokenExpried() {
                Log.d(TAG, "onSTSTokenExpried");
                //需要重新获取STStoken
                try {
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        //STS token过期之后刷新STStoken，如正在上传将会断点续传
//                        vodsVideoUploadClient.refreshSTSToken(accessKeyId, accessKeySecret, securityToken, expriedTime);---------------------------
                    }else
                        upFailed(true);

                } catch (IOException e) {
                    e.printStackTrace();
                    //失败，直接提示重新上传
                    upFailed(true);
                }
            }

            @Override
            public void onUploadRetry(String code, String message) {
                //上传重试的提醒
                Log.d(TAG, "onUploadRetry" + "code" + code + "message" + message);
            }

            @Override
            public void onUploadRetryResume() {
                //上传重试成功的回调.告知用户重试成功
                Log.d(TAG, "onUploadRetryResume");
            }
        });
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
    private void upStart(){
        aliyunVodPlayer.pause();
        videoPlayerView.getBinding().playerPlay.setVisibility(View.VISIBLE);
        videoPlayerView.getBinding().playerStop.setVisibility(View.GONE);
        videoPlayerView.getBinding().playerUpload.setClickable(false);
        progress=0;
        videoPlayerView.getBinding().playerUpload.setText("取消上传");
        videoPlayerView.getBinding().playerPlay.setClickable(false);
        videoPlayerView.getBinding().playerStop.setClickable(false);
        videoPlayerView.getBinding().videoUploadStatus.setVisibility(View.VISIBLE);
        videoPlayerView.getBinding().videoUploadStatus.setText("准备开始上传!");
        //上传到服务器
        try {
            client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    upFailed(true);
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    // 注：该回调是子线程，非主线程
                    Log.i("wxy", "callback thread id is " + Thread.currentThread().getId());
                    Log.i("wxy", response.body().string());
                    if(response.isSuccessful()) {
//                        genParm();--------------------
                    }
                    else{
                        upFailed(true);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //取sts失败，取消提示，可以再次上传
            upFailed(true);
        }

    }

    /**
     * 上传失败，提示用户
     */
    private void upFailed(boolean failed){
        videoPlayerView.getBinding().playerPlay.setClickable(true);
        videoPlayerView.getBinding().playerStop.setClickable(true);
        videoPlayerView.getBinding().playerUpload.setText("上传");
        if(failed){
            videoPlayerView.getBinding().videoUploadStatus.setText("上传失败，请重试！");
            mHandler.sendEmptyMessageDelayed(WHAT_UPFAIELD,2000);
        }else{
            videoPlayerView.getBinding().videoUploadStatus.setText("上传成功！！");
            mHandler.sendEmptyMessageDelayed(WHAT_SUCCESS,1000);
        }
    }

    /**
     * 取消上传
     */
    private void upCancel(){
        vodsVideoUploadClient.cancel();
        videoPlayerView.getBinding().playerUpload.setText("上传");
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

    //更新上传进度
    private final int WHAT_PROGRESS = 2;
    //上传失败
    private final int WHAT_UPFAIELD=3;
    //上传成功
    private final int WHAT_SUCCESS=4;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showProgress();
                    break;
                case WHAT_PROGRESS:
                    videoPlayerView.getBinding().videoUploadStatus.setText("上传进度："+progress+"%");
                    break;
                case WHAT_UPFAIELD:
                    videoPlayerView.getBinding().videoUploadStatus.setVisibility(View.GONE);
                    break;
                case WHAT_SUCCESS:
                    videoPlayerView.finish();
                    break;
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
