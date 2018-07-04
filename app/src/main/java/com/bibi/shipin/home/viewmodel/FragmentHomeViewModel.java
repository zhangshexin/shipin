package com.bibi.shipin.home.viewmodel;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.FragmentHome;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.home.viewmodel.adapter.HomeListAdapter;

import java.util.ArrayList;

/**
 * Created by zhangshexin on 2018/7/3.
 */

public class FragmentHomeViewModel extends BaseViewModel {
    private FragmentHome fragmentHome;
    private ArrayList<WorkBean> dataList = new ArrayList<>();
    private HomeListAdapter adapter;

    public FragmentHomeViewModel(FragmentHome fragmentHome) {
        super();
        this.fragmentHome = fragmentHome;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
    }

    private void initView() {
        for (int i = 0; i <= 10; i++) {
            WorkBean bean = new WorkBean();
            dataList.add(bean);
        }

        fragmentHome.getBinding().fragmentHomeList.setLayoutManager(new LinearLayoutManager(fragmentHome.getContext(), LinearLayoutManager.VERTICAL, false));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(fragmentHome.getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(fragmentHome.getContext(), R.drawable.drawable_divider_line));
        fragmentHome.getBinding().fragmentHomeList.addItemDecoration(divider);
        adapter = new HomeListAdapter(fragmentHome);
        adapter.mList.addAll(dataList);
        fragmentHome.getBinding().fragmentHomeList.setAdapter(adapter);
    }
}
