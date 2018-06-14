package com.bibi.shipin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhangshexin on 2018/6/11.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private String TAG=getClass().getName();

    private  List<MainFragment> list;
    public MainFragmentAdapter(FragmentManager fm,List<MainFragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public MainFragment getItem(int position) {
        Log.e(TAG, "getItem: "+position );
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
