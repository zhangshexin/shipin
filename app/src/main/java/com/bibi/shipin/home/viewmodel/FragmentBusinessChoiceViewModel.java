package com.bibi.shipin.home.viewmodel;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.BottomAdapter;
import com.bibi.shipin.home.FragmentBusinessChoice;
import com.bibi.shipin.home.FragmentHome;
import com.bibi.shipin.home.business.FragmentBusiness;
import com.bibi.shipin.home.business.FragmentBusinessStart;
import com.bibi.shipin.home.viewmodel.adapter.HomeListAdapter;

/**
 * Created by zhangshexin on 2018/7/5.
 */

public class FragmentBusinessChoiceViewModel extends BaseViewModel implements View.OnClickListener {
    private FragmentBusinessChoice fragmentBusinessChoice;
    public FragmentBusinessChoiceViewModel(FragmentBusinessChoice fragmentBusinessChoice) {
        this.fragmentBusinessChoice=fragmentBusinessChoice;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fragmentBusinessChoice.getBinding().setOnChoicnessClick(this);
        initData();
        initView();
    }

    private void initData() {
        BottomAdapter adapter = new BottomAdapter(fragmentBusinessChoice.getChildFragmentManager());
        adapter.addFragment(new FragmentBusiness());

        FragmentHome money= new FragmentHome();
        Bundle moneyBundle=new Bundle();
        moneyBundle.putInt("flag", HomeListAdapter.FLAG_CREATE);
        money.setArguments(moneyBundle);
        adapter.addFragment(money);

        FragmentBusiness fragmentBusinessNew=new FragmentBusiness();
        Bundle newBundle=new Bundle();
        newBundle.putBoolean("isNew",true);
        fragmentBusinessNew.setArguments(newBundle);
        adapter.addFragment(fragmentBusinessNew);
        fragmentBusinessChoice.getBinding().fragmentChoicenessViewpager.setAdapter(adapter);

        fragmentBusinessChoice.getBinding().fragmentChoicenessViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchChoice(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //商业
            case R.id.fragment_choiceness_buy:
                switchChoice(0);
                break;
            //创业
            case R.id.fragment_choiceness_create:
                switchChoice(1);
                break;
            //新知
            case R.id.fragment_choiceness_new:
                switchChoice(2);
                break;
        }
    }

    /**
     * 选中效果
     * @param position
     */
    private void switchChoice(int position){
        if(fragmentBusinessChoice.getBinding().fragmentChoicenessViewpager.getCurrentItem()!=position)
            fragmentBusinessChoice.getBinding().fragmentChoicenessViewpager.setCurrentItem(position);
        fragmentBusinessChoice.getBinding().fragmentChoicenessBuy.setTextColor(position==0?fragmentBusinessChoice.getResources().getColor(R.color.home_item_name_color):fragmentBusinessChoice.getResources().getColor(R.color.bottom_text_color));
        fragmentBusinessChoice.getBinding().fragmentChoicenessCreate.setTextColor(position==1?fragmentBusinessChoice.getResources().getColor(R.color.home_item_name_color):fragmentBusinessChoice.getResources().getColor(R.color.bottom_text_color));
        fragmentBusinessChoice.getBinding().fragmentChoicenessNew.setTextColor(position==2?fragmentBusinessChoice.getResources().getColor(R.color.home_item_name_color):fragmentBusinessChoice.getResources().getColor(R.color.bottom_text_color));
    }
}
