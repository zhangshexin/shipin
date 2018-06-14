package com.bibi.shipin;

import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.bibi.shipin.base.BaseFragment;
import com.bibi.shipin.databinding.ItemPlayerBinding;
import com.bibi.shipin.player.PlayerBean;

/**
 * Created by zhangshexin on 2018/6/11.
 */

public class MainFragment extends BaseFragment<ItemPlayerBinding,MainFragmentViewModel> {


    @Override
    public int onCreateSetView() {
        return 0;
    }

    @Override
    public MainFragmentViewModel onCreateSetViewModel() {
        return null;
    }

    @Override
    public void doOnCreateFunction() {

    }

    @Override
    public int initContentView() {
        return R.layout.item_player;
    }

    @Override
    public int initVariableId() {
        return BR.mainFragmentVM;
    }
    private AliyunVodPlayer aliyunVodPlayer;

    public void setAliyunVodPlayer(AliyunVodPlayer aliyunVodPlayer) {
        this.aliyunVodPlayer = aliyunVodPlayer;
    }

    public AliyunVodPlayer getAliyunVodPlayer() {
        return aliyunVodPlayer;
    }

    @Override
    public MainFragmentViewModel initViewModel() {
        return new MainFragmentViewModel(this,(PlayerBean)getArguments().getSerializable("bean"));
    }
}
