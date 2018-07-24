package com.bibi.shipin.mine;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseView;
import com.bibi.shipin.databinding.LayoutSettingBinding;
import com.bibi.shipin.mine.viewmodel.SettingViewModel;

import java.io.Serializable;

/**
 * Created by zhangshexin on 2018/7/20.
 * 设置
 */

public class SettingView extends BaseView<LayoutSettingBinding,SettingViewModel>{
    public static final String EXTRA_TAG="SettingView_1";
    public static enum Setting implements Serializable{
        ACCOUNT("账号设置",0),SECURITY("账号安全设置",1),MESSAGE("消息设置",2),PRIVACY("隐私设置",3),GENERAL("通用设置",4);
        private String title;
        private int code;
        Setting(String title,int code){
            this.title=title;
            this.code=code;
        }
        public String getTitle(){
            return title;
        }
        public int getCode(){
            return code;
        }

        /**
         * 跟据code取标题
         * @param code
         * @return
         */
        public String getTitleByCode(int code){
            for (Setting setting:Setting.values()){
                if(setting.code==code)
                    return setting.getTitle();
            }
            return null;
        }
    }
    @Override
    public int onCreateSetView() {
        return R.layout.layout_setting;
    }

    @Override
    public SettingViewModel onCreateSetViewModel() {
        return new SettingViewModel(this);
    }
}
