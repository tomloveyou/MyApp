package com.yl.triplibrary.ui.activity;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.contract.RankTripContract;
import com.yl.triplibrary.net.data.mvp.presenter.RankTripPresenter;
import com.yl.triplibrary.ui.activity.adapter.RankTripAdapter2;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.util.V;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanscadeFragment extends BaseFuncFragment<RankTripPresenter> implements RankTripContract.RankTripView {


    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RankTripAdapter2 tripAdapter2;
    List<RankTripZone> da = new ArrayList<>();

    @Override
    protected RankTripPresenter getPresenter() {
        return new RankTripPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lanscade;
    }

    @Override
    public void init() {
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        tripAdapter2 = new RankTripAdapter2(da);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(manager);
        tripAdapter2.bindToRecyclerView(myRecyclerView);
        mPresenter.getRankTripData(true);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getRankTripData(false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getRankTripData(false);
            }
        });
    }


    @Override
    public void getRankTrip(List<RankTripZone> data) {

        if (mPresenter.getCurrent_page()==1){
            tripAdapter2.setNewData(data);
            smartRefreshLayout.finishRefresh();
        }else {
            tripAdapter2.addData(data);
            smartRefreshLayout.finishLoadmore();
        }

      //  tripAdapter2.notifyDataSetChanged();
    }
}
