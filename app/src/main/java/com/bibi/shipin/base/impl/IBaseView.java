package com.bibi.shipin.base.impl;

import com.bibi.shipin.base.BaseViewModel;

/**
 * Created by zhangshexin on 2018/6/8.
 */

public interface IBaseView<V extends BaseViewModel> {

    /**
     * 此处设置xml布局文件的源
     */
    public int onCreateSetView();

    /**
     * r此处设置vm，进行初始化
     * @return
     */
    public V onCreateSetViewModel();

    /**
     * 期他一些必需要在onCreate方法中执行的
     */
    public void doOnCreateFunction();
}
