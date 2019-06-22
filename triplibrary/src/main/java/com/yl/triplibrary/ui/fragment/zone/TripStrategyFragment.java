package com.yl.triplibrary.ui.fragment.zone;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.contract.RankTripContract;
import com.yl.triplibrary.net.data.mvp.contract.TripStrategyContract;
import com.yl.triplibrary.net.data.mvp.module.TripStrategyEntity;
import com.yl.triplibrary.net.data.mvp.presenter.RankTripPresenter;
import com.yl.triplibrary.net.data.mvp.presenter.TripStrategyPresenter;
import com.yl.triplibrary.ui.activity.adapter.RankTripAdapter2;
import com.yl.triplibrary.ui.activity.adapter.TipStratygyImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripStrategyTitleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 旅游攻略
 *
 * @user yl
 * @date 11:25
 **/
public class TripStrategyFragment extends BaseFuncFragment<TripStrategyPresenter> implements TripStrategyContract.TTripStrategyView {


    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private DelegateAdapter adapters;

    @Override
    protected TripStrategyPresenter getPresenter() {
        return new TripStrategyPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lanscade;
    }

    @Override
    public void init() {
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);

        myRecyclerView.setLayoutManager(layoutManager);

        adapters = new DelegateAdapter(layoutManager, false);
        myRecyclerView.setAdapter(adapters);
        mPresenter.getLanScadeDeailData(true);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getLanScadeDeailData(false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getLanScadeDeailData(false);
            }
        });

    }


    @Override
    public void getTripStrategyData(List<TripStrategyEntity> data) {

        for (TripStrategyEntity tripStrategyEntity : data) {
            SingleLayoutHelper titleHelp = new SingleLayoutHelper();
            TripStrategyTitleAdapter titleAdapter = new TripStrategyTitleAdapter(mContext, titleHelp, tripStrategyEntity.getTitle());
            adapters.addAdapter(titleAdapter);

            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
            TipStratygyImgAdapter imgAdapter = new TipStratygyImgAdapter(mContext, gridLayoutHelper, tripStrategyEntity.getImgInfoEntityList());
            adapters.addAdapter(imgAdapter);
        }
        adapters.notifyDataSetChanged();
        //  tripAdapter2.notifyDataSetChanged();
    }
}
