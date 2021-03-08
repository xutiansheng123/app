package com.ta.xutiansheng.xtsapp.fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.mvp.BaseFragment;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import butterknife.BindView;

public class OrderFragment extends BaseFragment {
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.viewpage)
    ViewPager viewpage;

    @Override
    protected void initview() {
        Bundle bundle = new Bundle();
        bundle.putInt("state", 0);
        Bundle bundle1 = new Bundle();
        bundle1.putInt("state", 6);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("state", 7);
        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getFragmentManager(), FragmentPagerItems.with(context)
                .add("全部", OrderStateFragment.class)
                .add("待消费", OrderStateFragment.class, bundle)
                .add("待评价", OrderStateFragment.class, bundle1)
                .add("退款", OrderStateFragment.class, bundle2).create());
        viewpage.setAdapter(fragmentPagerItemAdapter);
        viewpage.setOffscreenPageLimit(3);
        viewpagertab.setViewPager(viewpage);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutid() {
        return R.layout.orderfragment;
    }
}
