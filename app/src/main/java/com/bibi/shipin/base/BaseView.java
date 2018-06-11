package com.bibi.shipin.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bibi.shipin.base.impl.IBaseView;
import com.bibi.shipin.util.Util;


/**
 * Created by zhangshexin on 2018/6/8.
 */

public class BaseView<V extends ViewDataBinding,M extends BaseViewModel> extends AppCompatActivity implements IBaseView<M> {

    private String TAG=getClass().getName();
    public void logE(String e){
        Log.e(TAG, "\n错误提示："+e );
    }
    public void logI(String info){
        Log.i(TAG, "\n日志提示: "+info);
    }



    private V binding;
    private M vm;


    public V getBinding() {
        return binding;
    }

    public M getVm() {
        return vm;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doOnCreateFunction();
        try {
            binding=DataBindingUtil.setContentView(this,onCreateSetView());
        } catch (Exception e) {
            e.printStackTrace();
            logE("没有调用onCreateSetView()方法，无法初始化");
        }

        vm=onCreateSetViewModel();
        if(Util.isEmpty(vm))
            logE("没有调用onCreateSetView()方法，无法初始化");

        vm.onCreate();
    }

    @Override
    public int onCreateSetView() {
        return 0;
    }

    @Override
    public M onCreateSetViewModel() {
        return null;
    }

    @Override
    public void doOnCreateFunction() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        vm.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vm.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vm.onDestory();
    }
}
