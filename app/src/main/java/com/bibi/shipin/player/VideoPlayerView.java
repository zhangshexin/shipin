package com.bibi.shipin.player;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutVideoPlayerBinding;

/**
 * Created by zhangshexin on 2018/6/22.
 *
 * 对于需要单独播放的内容在此处进行播放
 */

public class VideoPlayerView extends BaseView<LayoutVideoPlayerBinding,VideoPlayerViewModel>{
    @Override
    public int onCreateSetView() {
        return R.layout.layout_video_player;
    }

    @Override
    public VideoPlayerViewModel onCreateSetViewModel() {
        return new VideoPlayerViewModel(this);
    }
}
