package com.bibi.shipin.mine.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.mine.MyCollectionView;
import com.bibi.shipin.mine.adapter.CollectionAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshexin on 2018/7/12.
 * 我的收藏
 */

public class MyCollectionViewModel extends BaseViewModel implements View.OnClickListener {
    private List<WorkBean> dataList = new ArrayList<>();

    private MyCollectionView myAttentionView;
    private CollectionAdapter adapter;

    public MyCollectionViewModel(MyCollectionView myAttentionView) {
        this.myAttentionView = myAttentionView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myAttentionView.getBinding().collectionTitleBar.titleBarTitle.setText(myAttentionView.getTitle().toString());
        myAttentionView.getBinding().collectionTitleBar.titleBarBtnLeft.setOnClickListener(this);
        loadData();
        adapter = new CollectionAdapter(myAttentionView, CollectionAdapter.TAG_MY_ATTENTION);
        myAttentionView.getBinding().collectionList.setLayoutManager(new LinearLayoutManager(myAttentionView, LinearLayoutManager.VERTICAL, false));
        adapter.mList = dataList;
        myAttentionView.getBinding().collectionList.setAdapter(adapter);
    }

    private void loadData() {
        for (int i = 0; i <= 10; i++) {
            WorkBean bean = new WorkBean();
            dataList.add(bean);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_btn_left:
                myAttentionView.finish();
                break;
        }
    }
}
