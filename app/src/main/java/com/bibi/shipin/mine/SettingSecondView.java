package com.bibi.shipin.mine;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutSettingSecondPageBinding;
import com.bibi.shipin.mine.viewmodel.SettingSecondViewModel;

import java.io.Serializable;

/**
 * Created by zhangshexin on 2018/7/24.
 * 设置的二级页
 */

public class SettingSecondView extends BaseView<LayoutSettingSecondPageBinding,SettingSecondViewModel>{
    public static final String EXTRA_TAG="SettingSecondView_1";

    public static enum  SettingSecond implements Serializable{
        ACCOUNT("个人信息",0),GENERAL_WIFI("WIFI设置",1),GENERAL_SIZE("字体设置",2),MESSAGE_AT("\\@设置",3);
        private String title;
        private int code;
        SettingSecond(String title,int code){
            this.code=code;
            this.title=title;
        }

        public String getTitle() {
            return title;
        }

        public int getCode() {
            return code;
        }
    }

    @Override
    public int onCreateSetView() {
        return R.layout.layout_setting_second_page;
    }

    @Override
    public SettingSecondViewModel onCreateSetViewModel() {
        return new SettingSecondViewModel(this);
    }
}
