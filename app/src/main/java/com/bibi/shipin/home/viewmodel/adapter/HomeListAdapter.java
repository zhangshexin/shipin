package com.bibi.shipin.home.viewmodel.adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bibi.shipin.R;
import com.bibi.shipin.base.BaseAdapter;
import com.bibi.shipin.base.BaseViewHolder;
import com.bibi.shipin.databinding.LayoutFragmentHomeListItemBinding;
import com.bibi.shipin.home.FragmentHome;
import com.bibi.shipin.home.HomeView;
import com.bibi.shipin.home.HomeViewModel;
import com.bibi.shipin.home.SomebodyHomePageView;
import com.bibi.shipin.home.beans.WorkBean;

/**
 * Created by zhangshexin on 2018/7/4.
 * <p>
 * <p>%N代表第N个参数，如%3代表的是第三个参数；</>
 * <p>
 * $是结束符；
 * <p>
 * d/s/.2f代表的是整数/字符串/保留2位小数点的浮点数
 */

public class HomeListAdapter extends BaseAdapter<WorkBean, BaseViewHolder> implements View.OnClickListener{

    private FragmentHome fragmentHome;
    //是否为创业领袖页
    public static final int FLAG_CREATE = 0;
    //挖矿
    public static final int FLAG_MONEY = 1;
    //首页
    public static final int FLAG_HOME = 2;
    private int flag = FLAG_CREATE;

    private HomeViewModel vm;

    public HomeListAdapter(FragmentHome context, int flag) {
        super(context.getContext());
        this.fragmentHome = context;
        this.flag = flag;
        vm=((HomeView)context.getActivity()).getVm();
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        LayoutFragmentHomeListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_home_list_item, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        LayoutFragmentHomeListItemBinding binding = (LayoutFragmentHomeListItemBinding) baseViewHolder.getBinding();
        binding.setHomeAdapterClick(this);
        switch (flag) {
            case FLAG_CREATE:
                binding.homeListItemBtnMoney.setVisibility(View.GONE);
                break;
            case FLAG_MONEY:
                binding.homeListItemBtnMoney.setText(R.string.make_money);
                break;
            case FLAG_HOME:
                binding.homeListItemBtnMoney.setText(String.format(fragmentHome.getString(R.string.money),100));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_photo:
                goSomeBodyHomePage();
                break;
            case R.id.home_list_item_icon_comment:
                //弹出评论
                vm.toggleCommentList(true,null);
                break;
            case R.id.home_list_item_icon_dianzan:
                //点赞
                break;
            case R.id.home_list_item_icon_share:
                //分享
                vm.toggleShare(true,null);
                break;
        }
    }

    private void goSomeBodyHomePage() {
        fragmentHome.getContext().startActivity(new Intent(fragmentHome.getContext(), SomebodyHomePageView.class));
    }
}
