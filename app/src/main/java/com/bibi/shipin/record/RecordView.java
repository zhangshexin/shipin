package com.bibi.shipin.record;


import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.RecordViewBinding;

/**
 * Created by zhangshexin on 2018/6/19.
 *
 * 录制页
 */

public class RecordView extends BaseView<RecordViewBinding,RecordViewModel> {
    @Override
    public int onCreateSetView() {
        return R.layout.record_view;
    }

    @Override
    public RecordViewModel onCreateSetViewModel() {
        return new RecordViewModel(this);
    }
}
