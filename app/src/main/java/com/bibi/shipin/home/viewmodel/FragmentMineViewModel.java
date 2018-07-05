package com.bibi.shipin.home.viewmodel;

import android.content.Intent;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.FragmentMine;
import com.bibi.shipin.login.LoginView;

/**
 * Created by zhangshexin on 2018/7/4.
 */

public class FragmentMineViewModel extends BaseViewModel implements View.OnClickListener{

    private FragmentMine fragmentMine;
    public FragmentMineViewModel(FragmentMine fragmentMine) {
        super();
        this.fragmentMine=fragmentMine;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fragmentMine.getBinding().setOnMineClick(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mine_photo:
                goLogin();
                break;
        }
    }

    private void goLogin() {
        fragmentMine.startActivity(new Intent(fragmentMine.getContext(), LoginView.class));
    }
}
