package com.bibi.shipin.mine;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutMyFansAttentionsBinding;
import com.bibi.shipin.mine.viewmodel.MyFansAndAttentionsViewModel;

/**
 * Created by zhangshexin on 2018/7/12.
 * 我的粉丝和关注
 */

public class MyFansAndAttentionsView extends BaseView<LayoutMyFansAttentionsBinding,MyFansAndAttentionsViewModel>{
    @Override
    public int onCreateSetView() {
        return R.layout.layout_my_fans_attentions;
    }

    @Override
    public MyFansAndAttentionsViewModel onCreateSetViewModel() {
        return new MyFansAndAttentionsViewModel(this);
    }
}
