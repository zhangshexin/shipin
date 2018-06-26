package com.bibi.shipin.record;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.aliyun.recorder.AliyunRecorderCreator;
import com.aliyun.recorder.supply.AliyunIClipManager;
import com.aliyun.recorder.supply.AliyunIRecorder;
import com.aliyun.recorder.supply.RecordCallback;
import com.aliyun.struct.recorder.CameraParam;
import com.aliyun.struct.recorder.CameraType;
import com.aliyun.struct.recorder.MediaInfo;
import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.player.VideoPlayerView;
import com.qu.preview.callback.OnFrameCallBack;

import java.io.File;
import java.util.List;

/**
 * Created by zhangshexin on 2018/6/19.
 */

public class RecordViewModel extends BaseViewModel implements View.OnClickListener{
    private String TAG=getClass().getName();
    private RecordView recordView;
    private AliyunIRecorder aliyunIRecorder;
    private AliyunIClipManager aliyunIClipManager;
    //默认开启前摄像头
    private boolean frontCamera;
    //先定为10分钟
    private int maxDuration=10*60*1000;
    private int minDuration=1*1000;

    //输出路径
    private String outPuthPath = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM+ File.separator +"dashipin" + File.separator + System.currentTimeMillis() + ".mp4";

    public RecordViewModel(RecordView recordView) {
        this.recordView = recordView;
        File outFile=new File(outPuthPath.substring(0,outPuthPath.lastIndexOf(File.separator)));
        if(!outFile.isDirectory())
            outFile.mkdirs();
        recordView.getBinding().setRecordViewOnclick(this);
        aliyunIRecorder = AliyunRecorderCreator.getRecorderInstance(recordView);

        //设置必要的参数
        aliyunIRecorder.setDisplayView(recordView.getBinding().recordSurface);
        aliyunIRecorder.setMediaInfo(genMediaInfo());

        //录制的角度
//        aliyunIRecorder.setRotation(90);
        //输出的路径
        aliyunIRecorder.setOutputPath(outPuthPath);
        //美颜
        aliyunIRecorder.setBeautyLevel(80);

        aliyunIRecorder.setFaceTrackInternalMaxFaceCount(2);

        aliyunIRecorder.setFocusMode(CameraParam.FOCUS_MODE_CONTINUE);
        //设置回调
        aliyunIRecorder.setRecordCallback(new myRecordCallBack());
        aliyunIRecorder.setOnFrameCallback(new myOnFrameCallBack());

        aliyunIClipManager=aliyunIRecorder.getClipManager();
        aliyunIClipManager.setMaxDuration(maxDuration);
        aliyunIClipManager.setMinDuration(minDuration);
        recordView.getBinding().recordProgress.setMax(maxDuration);

    }

    /**
     * 切换前后摄像头
     */
    public void changeCamera() {
        if (frontCamera) {
            frontCamera = false;
        } else {
            frontCamera = true;
        }
        aliyunIRecorder.switchCamera();
    }

    /**
     * 开始录相
     */
    public void recordStart() {
        recordView.getBinding().recordStop.setVisibility(View.VISIBLE);
        recordView.getBinding().recordStart.setVisibility(View.GONE);
        //引藏结束按钮
        recordView.getBinding().recordFinish.setVisibility(View.INVISIBLE);
        aliyunIRecorder.startRecording();
    }

    /**
     * 停止录像
     */
    public void recordStop() {
        recordView.getBinding().recordStop.setVisibility(View.GONE);
        recordView.getBinding().recordStart.setVisibility(View.VISIBLE);
        //显示结束按钮
        recordView.getBinding().recordFinish.setVisibility(View.VISIBLE);
        aliyunIRecorder.stopRecording();
    }

    /**
     * 录像结束
     */
    public void recordFinish(){
        recordView.getBinding().recordFinishText.setVisibility(View.VISIBLE);
        recordView.getBinding().recordStop.setVisibility(View.GONE);
        recordView.getBinding().recordStart.setVisibility(View.GONE);
        recordView.getBinding().recordChangCamera.setVisibility(View.GONE);
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                aliyunIRecorder.finishRecording();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    private void doChangeCamera() {
        if (frontCamera) {
            aliyunIRecorder.setCamera(CameraType.FRONT);
        } else {

            aliyunIRecorder.setCamera(CameraType.BACK);
        }
    }

    /**
     * 主要用来设置宽高
     *
     * @return
     */
    private MediaInfo genMediaInfo() {
        // 取屏宽高
        DisplayMetrics dm = recordView.getResources().getDisplayMetrics();
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setVideoHeight(dm.heightPixels);
        mediaInfo.setVideoWidth(dm.widthPixels);
        mediaInfo.setHWAutoSize(true);
        return mediaInfo;
    }

    @Override
    public void onResume() {
        doChangeCamera();
        aliyunIRecorder.startPreview();
        super.onResume();
    }

    @Override
    public void onPause() {
        doChangeCamera();
        aliyunIRecorder.stopPreview();
        super.onPause();
    }

    @Override
    public void onDestory() {
        //清除片段
        aliyunIClipManager.deleteAllPart();
        //消毁实例
        AliyunRecorderCreator.destroyRecorderInstance();
        super.onDestory();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.record_chang_camera:
                changeCamera();
                break;
            case R.id.record_finish:
                recordFinish();
                break;
            case R.id.record_start:
                recordStart();
                break;
            case R.id.record_stop:
                recordStop();
                break;
        }
    }


    //========================================回调========================================

    private class myRecordCallBack implements RecordCallback{
        /**
         * 录制完毕的回调
         */
        @Override
        public void onComplete(boolean b, long l) {
            Log.e(TAG, b+"    onComplete:    "+l);
        }
        /**
         * 合成完毕的回调
         * @param outputPath
         */
        @Override
        public void onFinish(String outputPath) {
            Log.e(TAG, "onFinish: "+outputPath);
            Intent goVideoPlayer=new Intent(recordView, VideoPlayerView.class);
            goVideoPlayer.putExtra("outputPath",outputPath);
            recordView.startActivity(goVideoPlayer);
            recordView.finish();

        }
        /**
         * 录制进度回调
         * @param duration 当前已录制时间
         */
        @Override
        public void onProgress(long duration) {
            int recordTime = (int) duration + aliyunIClipManager.getDuration();
            recordView.getBinding().recordProgress.setProgress(recordTime);
            Log.e(TAG, "onProgress: "+duration);
        }
        /**
         * 达到最大时长
         */
        @Override
        public void onMaxDuration() {
            recordStop();
            recordFinish();
        }
        /**
         * 录制错误回调
         * @param errorCode
         */
        @Override
        public void onError(int errorCode) {
            Log.e(TAG, "onError: =================="+errorCode);
        }
        /**
         * 录制初始化回调
         */
        @Override
        public void onInitReady() {

        }
        /**
         * 该回调后可以调用{@link AliyunIRecorder#startRecording()}接口
         */
        @Override
        public void onDrawReady() {
            recordView.getBinding().recordStart.setClickable(true);
        }
        /**
         * 获取当前渲染帧并转成bitmap
         * @param bitmap
         */
        @Override
        public void onPictureBack(Bitmap bitmap) {

        }
        /**
         * 获取当前渲染帧数据
         * @param data
         */
        @Override
        public void onPictureDataBack(byte[] data) {

        }
    }

    private class myOnFrameCallBack implements OnFrameCallBack{
        @Override
        public void onFrameBack(byte[] bytes, int i, int i1, Camera.CameraInfo cameraInfo) {

        }

        @Override
        public Camera.Size onChoosePreviewSize(List<Camera.Size> list, Camera.Size size) {
            return null;
        }

        @Override
        public void openFailed() {

        }
    }
}
