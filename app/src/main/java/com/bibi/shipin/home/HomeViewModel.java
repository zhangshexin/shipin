package com.bibi.shipin.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseViewModel;
import com.bibi.shipin.home.beans.WorkBean;
import com.bibi.shipin.home.viewmodel.adapter.CommentAdapter;
import com.bibi.shipin.home.viewmodel.adapter.HomeListAdapter;
import com.bibi.shipin.record.RecordView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        homeView.getBinding().homeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchCheckStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onBackPress() {
        if(homeView.getBinding().homeIncludeComment.includeComment.getVisibility()==View.VISIBLE){
            toggleCommentList(false,null);
            return;
        }
        if(homeView.getBinding().homeIncludeShare.includeShare.getVisibility()==View.VISIBLE){
            toggleShare(false,null);
            return;
        }
        homeView.finish();
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
            case R.id.home_bottom_lay_plus:
                //去录制页
                homeView.startActivity(new Intent(homeView, RecordView.class));
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

    /**
     * 显示分享页面
     * @param show
     * @param shareContent
     */
    public void toggleShare(boolean show,String shareContent){
        homeView.getBinding().homeIncludeShare.includeShare.setVisibility(show?View.VISIBLE:View.GONE);
        homeView.getBinding().homeIncludeComment.includeComment.setVisibility(View.GONE);
    }


    private  CommentAdapter commentAdapter;
    /**
     *
     * @param show
     * @param url
     */
    public void toggleCommentList(boolean show,String url){
        if(show&&homeView.getBinding().homeIncludeComment.includeComment.getVisibility()==View.VISIBLE)
            return;
        homeView.getBinding().homeIncludeShare.includeShare.setVisibility(View.GONE);
        homeView.getBinding().homeIncludeComment.includeComment.setVisibility(show?View.VISIBLE:View.GONE);

        if(commentAdapter!=null){
            return;
        }
        //模似一下数据
        List comments= new ArrayList();
        for (int i=0;i<10;i++){
            WorkBean bean=new WorkBean();
            comments.add(bean);
        }
        LinearLayoutManager manager=new LinearLayoutManager(homeView,LinearLayoutManager.VERTICAL,false);
        commentAdapter=new CommentAdapter(homeView);
        commentAdapter.mList.addAll(comments);
        homeView.getBinding().homeIncludeComment.includeCommentList.setLayoutManager(manager);
        homeView.getBinding().homeIncludeComment.includeCommentList.setAdapter(commentAdapter);

    }

    private void setupViewPager(ViewPager viewPager) {
        BottomAdapter adapter = new BottomAdapter(homeView.getSupportFragmentManager());
        FragmentHome home= new FragmentHome();
        Bundle homeBundle=new Bundle();
        homeBundle.putInt("flag", HomeListAdapter.FLAG_HOME);
        home.setArguments(homeBundle);
        adapter.addFragment(home);

        adapter.addFragment(new FragmentBusinessChoice());

        FragmentHome money= new FragmentHome();
        Bundle moneyBundle=new Bundle();
        moneyBundle.putInt("flag", HomeListAdapter.FLAG_MONEY);
        money.setArguments(moneyBundle);
        adapter.addFragment(money);

        adapter.addFragment(new FragmentMine());
        viewPager.setAdapter(adapter);
    }
}
