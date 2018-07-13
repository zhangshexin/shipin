package com.bibi.shipin.home.viewmodel;

import android.content.Intent;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.FragmentMine;
import com.bibi.shipin.login.LoginView;
import com.bibi.shipin.mine.MyCollectionView;
import com.bibi.shipin.mine.MyFansAndAttentionsView;
import com.bibi.shipin.mine.adapter.MyFansAndAttentionsAdapter;
import com.bibi.shipin.mine.viewmodel.MyFansAndAttentionsViewModel;

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
            case R.id.mine_btn_attention:
                goAttention();
                break;
            case R.id.mine_fans_btn:
                goFansAttentions(1);
                break;
            case R.id.mine_attentions_btn:
                goFansAttentions(2);
                break;
        }
    }

    private void goFansAttentions(int tag) {
        int ext;
        if(tag==1)
            ext= MyFansAndAttentionsAdapter.TAG_FANS;
        else
            ext=MyFansAndAttentionsAdapter.TAG_ATTENTIONS;
        Intent fans=new Intent(fragmentMine.getContext(), MyFansAndAttentionsView.class);
        fans.putExtra(MyFansAndAttentionsViewModel.TAG,ext);
        fragmentMine.startActivity(fans);

    }

    private void goAttention() {
        fragmentMine.startActivity(new Intent(fragmentMine.getContext(), MyCollectionView.class));
    }

    private void goLogin() {
        fragmentMine.startActivity(new Intent(fragmentMine.getContext(), LoginView.class));
    }
}
