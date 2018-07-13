package com.bibi.shipin.home.viewmodel;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.FragmentHome;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.home.viewmodel.adapter.HomeListAdapter;

import java.util.ArrayList;

/**
 * Created by zhangshexin on 2018/7/3.
 * 首页/创业领袖/挖矿 --共用页
 */

public class FragmentHomeViewModel extends BaseViewModel {
    private FragmentHome fragmentHome;
    private ArrayList<WorkBean> dataList = new ArrayList<>();
    private HomeListAdapter adapter;

    private int flag;

    public FragmentHomeViewModel(FragmentHome fragmentHome) {
        super();
        this.fragmentHome = fragmentHome;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Bundle bundle=fragmentHome.getArguments();
        if(bundle!=null){
            flag=bundle.getInt("flag");
        }
        initView();
    }

    private void initView() {
        if(flag==HomeListAdapter.FLAG_MONEY){//挖矿的要显示搜索框
            fragmentHome.getBinding().fragmentHomeListSearch.setVisibility(View.VISIBLE);
            fragmentHome.getBinding().fragmentHomeListSearchIcon.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i <= 10; i++) {
            WorkBean bean = new WorkBean();
            dataList.add(bean);
        }

        fragmentHome.getBinding().fragmentHomeList.setLayoutManager(new LinearLayoutManager(fragmentHome.getContext(), LinearLayoutManager.VERTICAL, false));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(fragmentHome.getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(fragmentHome.getContext(), R.drawable.drawable_divider_line));
        fragmentHome.getBinding().fragmentHomeList.addItemDecoration(divider);
        adapter = new HomeListAdapter(fragmentHome,flag);
        adapter.mList.addAll(dataList);
        fragmentHome.getBinding().fragmentHomeList.setAdapter(adapter);
    }
}
