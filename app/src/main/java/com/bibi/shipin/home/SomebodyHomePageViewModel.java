package com.bibi.shipin.home;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.home.viewmodel.adapter.SomeHomeListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshexin on 2018/7/12.
 */

public class SomebodyHomePageViewModel extends BaseViewModel implements View.OnClickListener {

    private SomebodyHomePageView somebodyHomePageView;
    private List dataList=new ArrayList<WorkBean>();
    private SomeHomeListAdapter adapter;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            loadData();
            somebodyHomePageView.getBinding().somebodyLoadingProgress.setVisibility(View.GONE);
        }
    };

    public SomebodyHomePageViewModel(SomebodyHomePageView somebodyHomePageView) {
        super();
        this.somebodyHomePageView = somebodyHomePageView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
    }

    private void loadData() {
        for (int i = 0; i <= 10; i++) {
            WorkBean bean = new WorkBean();
            dataList.add(bean);
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        adapter=new SomeHomeListAdapter(somebodyHomePageView);
        adapter.mList=dataList;
        somebodyHomePageView.getBinding().setSomeBodyOnclick(this);
        LinearLayoutManager manager=new LinearLayoutManager(somebodyHomePageView,LinearLayoutManager.VERTICAL,false);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        somebodyHomePageView.getBinding().somebodyList.setLayoutManager(manager);
        somebodyHomePageView.getBinding().somebodyList.setHasFixedSize(true);
        somebodyHomePageView.getBinding().somebodyList.setNestedScrollingEnabled(false);
        somebodyHomePageView.getBinding().somebodyList.setAdapter(adapter);
        mHandler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.somebody_back_btn:
                somebodyHomePageView.finish();
                break;
        }
    }
}
