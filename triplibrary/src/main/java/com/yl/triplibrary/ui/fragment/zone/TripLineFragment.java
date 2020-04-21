package com.yl.triplibrary.ui.fragment.zone;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.presenter.TripLinePresenter;
import com.yl.triplibrary.ui.activity.TripLineDetailAcitivity;
import com.yl.triplibrary.ui.activity.adapter.TripLineAdapter;

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
    private String area_code="taiguo";
    @Override
    protected TripLinePresenter getPresenter() {
        return new TripLinePresenter(this);
    }

    public TripLineFragment getInstance(String url) {
        TripLineFragment baseFuncFragment = new TripLineFragment();
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
        area_code="/qu/"+area_code;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_refresh_recycleview;
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
        mPresenter.getLanScadeDeailData(false,area_code,true);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getLanScadeDeailData(false,area_code,false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getLanScadeDeailData(false,area_code,false);
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
