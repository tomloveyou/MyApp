package com.yl.triplibrary.ui.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.flyco.tablayout.SlidingTabLayout;
import com.ns.yc.ycutilslib.viewPager.NoSlidingViewPager;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;
import com.yl.triplibrary.ui.fragment.TripTabFragment;
import com.yl.triplibrary.ui.fragment.zone.LanscadeFragment;
import com.yl.triplibrary.ui.fragment.zone.TripFootPrintFragment;
import com.yl.triplibrary.ui.fragment.zone.TripLineFragment;
import com.yl.triplibrary.ui.fragment.zone.TripNoteFragment;
import com.yl.triplibrary.ui.fragment.zone.TripStrategyFragment;
import com.yl.triplibrary.ui.fragment.zone.TripWenDaFragment;

import java.util.ArrayList;
import java.util.List;

public class TripAareTedianActivity extends BaseTitleBarActivity {
    private SlidingTabLayout fragmentTab;
    private NoSlidingViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<HomeTripTab> tabData = new ArrayList<>();
    private TripAareTedianActivity.MyPageAdapter myPageAdapter;
    private String area_code="taiguo";
    private String area_name="独有特色";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trip_tab_layout;
    }

    @Override
    public void getExtra() {
        super.getExtra();
    }

    @Override
    public void init() {

        area_name=getIntent().getStringExtra("area_name");
        area_code=getIntent().getStringExtra("area_code");
        setTitle(area_name);
        fragmentTab = (SlidingTabLayout) findView(R.id.fragment_tab);
        viewPager = (NoSlidingViewPager) findView(R.id.fragment_containner);
        myPageAdapter = new TripAareTedianActivity.MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPageAdapter);
        fragmentTab.setViewPager(viewPager);
        getHomeTripTrip();
    }

    @Override
    public void setListener() {

    }



    public void getHomeTripTrip() {
        tabData.add(new HomeTripTab("路线"));
        fragmentList.add(new TripLineFragment().getInstance(area_code));
        tabData.add(new HomeTripTab("游记"));
        fragmentList.add(new TripNoteFragment());
        tabData.add(new HomeTripTab("攻略"));
        fragmentList.add(new TripStrategyFragment().getInstance(area_code));
        tabData.add(new HomeTripTab("景点"));
        fragmentList.add(new LanscadeFragment().getInstance(area_code));
        tabData.add(new HomeTripTab("问答"));
        fragmentList.add(new TripWenDaFragment().getInstance(area_code));
        tabData.add(new HomeTripTab("足迹"));
        fragmentList.add(new TripFootPrintFragment().getInstance(area_code));
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
