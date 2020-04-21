package com.yl.triplibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.presenter.TripLinePresenter;
import com.yl.triplibrary.ui.activity.adapter.TripLineAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchByAreaStartActivity extends BaseTitleBarActivity<TripLinePresenter>implements TripLineContract.TripLineView {
    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private TripLineAdapter tripAdapter2;
    private List<TripLineEntity> da = new ArrayList<>();
    private String area_code="taiguo";
    private String area_name="出发地";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_recycleview;
    }

    @Override
    public TripLinePresenter getPresenter() {
        return new TripLinePresenter(this);
    }

    @Override
    public void init() {
        area_name=getIntent().getStringExtra("area_name");
        area_code=getIntent().getStringExtra("area_code");
        area_code="/"+area_code;
        setTitle("从"+area_name+"出发");
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        tripAdapter2 = new TripLineAdapter(da);
        LinearLayoutManager manager = new LinearLayoutManager(SearchByAreaStartActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(manager);
        tripAdapter2.bindToRecyclerView(myRecyclerView);
        mPresenter.getLanScadeDeailData(true,area_code,true);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getLanScadeDeailData(true,area_code,false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getLanScadeDeailData(true,area_code,false);
            }
        });
        tripAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchByAreaStartActivity.this, TripLineDetailAcitivity.class);
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
