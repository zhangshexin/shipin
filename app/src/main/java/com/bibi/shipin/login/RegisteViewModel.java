package com.bibi.shipin.login;

import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;

/**
 * Created by zhangshexin on 2018/7/31.
 */

public class RegisteViewModel extends BaseViewModel implements View.OnClickListener {
    private RegisteView registeView;

    public RegisteViewModel(RegisteView registeView) {
        super();
        this.registeView=registeView;
        registeView.getBinding().setRegisteClick(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                registeView.finish();
                break;
        }
    }
}
