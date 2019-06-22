package com.yl.triplibrary.ui.fragment.zone;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.contract.RankTripContract;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.presenter.RankTripPresenter;
import com.yl.triplibrary.net.data.mvp.presenter.TripLinePresenter;
import com.yl.triplibrary.ui.activity.TripFootPrintDetailAcitivity;
import com.yl.triplibrary.ui.activity.TripLineDetailAcitivity;
import com.yl.triplibrary.ui.activity.adapter.RankTripAdapter2;
import com.yl.triplibrary.ui.activity.adapter.TripLineAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 旅游路线
*@user yl
*@date 11:26
**/
public class TripLineFragment extends BaseFuncFragment<TripLinePresenter> implements TripLineContract.TripLineView {


    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private TripLineAdapter tripAdapter2;
    private  List<TripLineEntity> da = new ArrayList<>();

    @Override
    protected TripLinePresenter getPresenter() {
        return new TripLinePresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lanscade;
    }

    @Override
    public void init() {
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        tripAdapter2 = new TripLineAdapter(da);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(manager);
        tripAdapter2.bindToRecyclerView(myRecyclerView);
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
        tripAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, TripLineDetailAcitivity.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", tripAdapter2.getItem(position).getGoto_ur());
                    intent.putExtras(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }


    @Override
    public void getTripLineData(List<TripLineEntity> data) {
        if (mPresenter.getCurrent_page() == 1) {
            tripAdapter2.setNewData(data);
            smartRefreshLayout.finishRefresh();
        } else {
            tripAdapter2.addData(data);
            smartRefreshLayout.finishLoadmore();
        }
    }
}
