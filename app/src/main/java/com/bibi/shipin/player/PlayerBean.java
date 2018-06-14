package com.bibi.shipin.player;


import java.io.Serializable;

/**
 * Created by zhangshexin on 2018/6/10.
 */

public class PlayerBean implements Serializable {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //播放地址
    private String url;
    //进度
    private int progress;
    //缩略图
    private String thumbUrl;

    private String title;


}
