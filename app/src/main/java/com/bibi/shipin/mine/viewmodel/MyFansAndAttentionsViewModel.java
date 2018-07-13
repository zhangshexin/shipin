package com.bibi.shipin.mine.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.mine.MyFansAndAttentionsView;
import com.bibi.shipin.mine.adapter.MyFansAndAttentionsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshexin on 2018/7/12.
 */

public class MyFansAndAttentionsViewModel extends BaseViewModel implements View.OnClickListener{
    public static final String   TAG   ="TAG";
    private int tag;
    private MyFansAndAttentionsView myFansAndAttentionsView;
    private MyFansAndAttentionsAdapter adapter;
    private List<WorkBean> dataList=new ArrayList<>();

    public MyFansAndAttentionsViewModel(MyFansAndAttentionsView myFansAndAttentionsView) {
        super();
        this.myFansAndAttentionsView=myFansAndAttentionsView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
        initView();
    }

    private void initView() {
        myFansAndAttentionsView.getBinding().fansAttentionsTitleBar.titleBarTitle.setText(tag==MyFansAndAttentionsAdapter.TAG_FANS?myFansAndAttentionsView.getString(R.string.my_fans):myFansAndAttentionsView.getString(R.string.my_attentions));
        myFansAndAttentionsView.getBinding().fansAttentionsTitleBar.titleBarBtnLeft.setOnClickListener(this);
        adapter=new MyFansAndAttentionsAdapter(myFansAndAttentionsView,tag);
        myFansAndAttentionsView.getBinding().fansAttentionsList.setLayoutManager(new LinearLayoutManager(myFansAndAttentionsView,LinearLayoutManager.VERTICAL,false));
        adapter.mList=dataList;
        myFansAndAttentionsView.getBinding().fansAttentionsList.setAdapter(adapter);

    }

    private void loadData() {
        tag=myFansAndAttentionsView.getIntent().getIntExtra(TAG, MyFansAndAttentionsAdapter.TAG_ATTENTIONS);
        for (int i=0;i<=10;i++){
            WorkBean bean=new WorkBean();
            dataList.add(bean);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_bar_btn_left:
                myFansAndAttentionsView.finish();
                break;
        }
    }
}
