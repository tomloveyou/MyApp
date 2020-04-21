package com.yl.triplibrary.ui.fragment.zone;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.contract.RankTripContract;
import com.yl.triplibrary.net.data.mvp.presenter.RankTripPresenter;
import com.yl.triplibrary.ui.activity.LanscadeDetailActivity;
import com.yl.triplibrary.ui.activity.adapter.RankTripAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 热门景点
 *
 * @user yl
 * @date 11:26
 **/
public class LanscadeFragment extends BaseFuncFragment<RankTripPresenter> implements RankTripContract.RankTripView {


    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RankTripAdapter2 tripAdapter2;
    private List<RankTripZone> da = new ArrayList<>();
     private String area_code="taiguo";

    public LanscadeFragment getInstance(String url) {
        LanscadeFragment baseFuncFragment = new LanscadeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("area_code", url);
        baseFuncFragment.setArguments(bundle);
        return baseFuncFragment;

    }
    @Override
    public void getExtra() {
        super.getExtra();
        Bundle bundle=getArguments();
        if (bundle!=null){
            area_code=bundle.getString("area_code");
        }
    }

    @Override
    protected RankTripPresenter getPresenter() {
        return new RankTripPresenter(this);
    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_refresh_recycleview;
    }

    @Override
    public void init() {
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        tripAdapter2 = new RankTripAdapter2(da);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(manager);
        tripAdapter2.bindToRecyclerView(myRecyclerView);
        mPresenter.getRankTripData(area_code,true);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getRankTripData(area_code,false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getRankTripData(area_code,false);
            }
        });
        tripAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, LanscadeDetailActivity.class);
                RankTripZone tripZone = tripAdapter2.getItem(position);
                if (tripZone != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", tripZone.getUrl());
                    intent.putExtras(bundle);
                }
                startActivity(intent);

            }
        });
    }


    @Override
    public void getRankTrip(List<RankTripZone> data) {

        if (mPresenter.getCurrent_page() == 1) {
            tripAdapter2.setNewData(data);
            smartRefreshLayout.finishRefresh();
        } else {
            tripAdapter2.addData(data);
            smartRefreshLayout.finishLoadmore();
        }

        //  tripAdapter2.notifyDataSetChanged();
    }
}
