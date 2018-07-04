package com.bibi.shipin.home;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;

/**
 * Created by zhangshexin on 2018/7/3.
 */

public class HomeViewModel extends BaseViewModel implements View.OnClickListener{

    private HomeView homeView;

    public HomeViewModel(HomeView homeView) {
        this.homeView=homeView;
        initView();
    }

    private void initView() {
        homeView.getBinding().setHomeOnclick(this);
        setupViewPager(homeView.getBinding().homeViewPager);
        //不左右滑
        homeView.getBinding().homeViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home_bottom_lay_home:
                homeView.getBinding().homeViewPager.setCurrentItem(0);
                switchCheckStatus(0);
                break;
            case R.id.home_bottom_lay_star:
                homeView.getBinding().homeViewPager.setCurrentItem(1);
                switchCheckStatus(1);
                break;
            case R.id.home_bottom_lay_buycar:
                homeView.getBinding().homeViewPager.setCurrentItem(2);
                switchCheckStatus(2);
                break;
            case R.id.home_bottom_lay_mine:
                homeView.getBinding().homeViewPager.setCurrentItem(3);
                switchCheckStatus(3);
                break;
        }
    }

    /**
     * 改变选择的底部按钮状态
     * @param position
     */
    private void switchCheckStatus(int position){
        homeView.getBinding().homeBottomLayHomeImg.setImageResource(position==0?R.drawable.icon_home_selected:R.drawable.icon_home_unselected);
        homeView.getBinding().homeBottomLayStarImg.setImageResource(position==1?R.drawable.icon_star_selected:R.drawable.icon_star_unselected);
        homeView.getBinding().homeBottomLayBuycarImg.setImageResource(position==2?R.drawable.icon_buycar_selected:R.drawable.icon_buycar_unselected);
        homeView.getBinding().homeBottomLayMineImg.setImageResource(position==3?R.drawable.icon_mine_selected:R.drawable.icon_mine_unselected);
    }

    private void setupViewPager(ViewPager viewPager) {
        BottomAdapter adapter = new BottomAdapter(homeView.getSupportFragmentManager());
        adapter.addFragment(new FragmentHome());
        adapter.addFragment(new FragmentStar());
        adapter.addFragment(new FragmentBuyCar());
        adapter.addFragment(new FragmentMine());
        viewPager.setAdapter(adapter);
    }
}
