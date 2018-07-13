package com.bibi.shipin.home.business.viewmodel;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.home.business.FragmentBusiness;
import com.bibi.shipin.home.business.adapter.BusinessListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshexin on 2018/7/5.
 * 商业
 */

public class FragmentBusinessViewModel extends BaseViewModel {
    private FragmentBusiness fragmentBusiness;
    private List<WorkBean> dataList = new ArrayList<>();
    private BusinessListAdapter adapter;

    //标记是否为新知
    private boolean isNew = false;

    public FragmentBusinessViewModel(FragmentBusiness fragmentBusiness) {
        super();
        this.fragmentBusiness = fragmentBusiness;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Bundle bundle = fragmentBusiness.getArguments();
        if (bundle != null) {
            isNew = bundle.getBoolean("isNew", false);
        }
        initData();
    }

    private void initData() {
        for (int i = 0; i <= 10; i++) {
            WorkBean bean = new WorkBean();
            dataList.add(bean);
        }

        fragmentBusiness.getBinding().fragmentListView.setLayoutManager(new LinearLayoutManager(fragmentBusiness.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new BusinessListAdapter(fragmentBusiness,isNew? BusinessListAdapter.TAG_NEW:BusinessListAdapter.TAG_BUSINESS);
        adapter.mList.addAll(dataList);
        fragmentBusiness.getBinding().fragmentListView.setAdapter(adapter);
    }
}
