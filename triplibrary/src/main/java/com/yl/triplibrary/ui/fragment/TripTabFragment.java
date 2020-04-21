package com.yl.triplibrary.ui.fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.flyco.tablayout.SlidingTabLayout;
import com.ns.yc.ycutilslib.viewPager.NoSlidingViewPager;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.HomeTripContract;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;
import com.yl.triplibrary.net.data.mvp.presenter.HomeTripPresenter;
import com.yl.triplibrary.ui.fragment.zone.LanscadeFragment;
import com.yl.triplibrary.ui.fragment.zone.TripFootPrintFragment;
import com.yl.triplibrary.ui.fragment.zone.TripLineFragment;
import com.yl.triplibrary.ui.fragment.zone.TripNoteFragment;
import com.yl.triplibrary.ui.fragment.zone.TripStrategyFragment;
import com.yl.triplibrary.ui.fragment.zone.TripWenDaFragment;

import java.util.ArrayList;
import java.util.List;

public class TripTabFragment extends BaseFuncFragment<HomeTripPresenter> implements HomeTripContract.HomeTripView {
    private SlidingTabLayout fragmentTab;
    private NoSlidingViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<HomeTripTab> tabData = new ArrayList<>();
    private MyPageAdapter myPageAdapter;

    @Override
    protected HomeTripPresenter getPresenter() {
        return new HomeTripPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trip_tab_layout;
    }

    @Override
    public void init() {
        fragmentTab = (SlidingTabLayout) findView(R.id.fragment_tab);
        viewPager = (NoSlidingViewPager) findView(R.id.fragment_containner);
        myPageAdapter = new MyPageAdapter(getFragmentManager());
        viewPager.setAdapter(myPageAdapter);
        fragmentTab.setViewPager(viewPager);
        mPresenter.getHomeTripData();
    }

    @Override
    public void setListener() {

    }


    @Override
    public void getHomeTripTrip(List<HomeTripTab> data) {
        tabData.clear();
        tabData.addAll(data);
        fragmentList.add(new TripLineFragment());
        fragmentList.add(new TripNoteFragment());
        fragmentList.add(new TripStrategyFragment());
        fragmentList.add(new LanscadeFragment());
        fragmentList.add(new TripWenDaFragment());
        fragmentList.add(new TripFootPrintFragment());
        myPageAdapter.notifyDataSetChanged();
        fragmentTab.notifyDataSetChanged();
    }

    class MyPageAdapter extends FragmentPagerAdapter {
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabData.get(position).getTab_title();
        }
    }
}
