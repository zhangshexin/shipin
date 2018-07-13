package com.bibi.shipin.mine;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutMyAttentionBinding;
import com.bibi.shipin.mine.viewmodel.MyCollectionViewModel;

/**
 * Created by zhangshexin on 2018/7/12.
 * 我的收藏
 */

public class MyCollectionView extends BaseView<LayoutMyAttentionBinding,MyCollectionViewModel>{
    @Override
    public int onCreateSetView() {
        return R.layout.layout_my_attention;
    }

    @Override
    public MyCollectionViewModel onCreateSetViewModel() {
        return new MyCollectionViewModel(this);
    }
}
