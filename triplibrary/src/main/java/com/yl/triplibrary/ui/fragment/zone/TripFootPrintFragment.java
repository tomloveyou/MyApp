package com.yl.triplibrary.ui.fragment.zone;


import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripFootPrintContract;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.presenter.TripFootPrintPresenter;
import com.yl.triplibrary.ui.activity.TripFootPrintDetailAcitivity;
import com.yl.triplibrary.ui.activity.adapter.TipStratygyImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripFootPrintItemEndAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripFootPrintItemHeadAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripStrategyTitleAdapter;
import com.yl.triplibrary.ui.widget.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 旅游足迹
 *
 * @user yl
 * @date 11:25
 **/
public class TripFootPrintFragment extends BaseFuncFragment<TripFootPrintPresenter> implements TripFootPrintContract.TripFootPrintView {


    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private DelegateAdapter adapters;
    private GestureDetector mGestureDetector;
    private List<TripFootPrintEntity> mDatas = new ArrayList<>();
    private String area_code="taiguo";

    public TripFootPrintFragment getInstance(String url) {
        TripFootPrintFragment baseFuncFragment = new TripFootPrintFragment();
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
    protected TripFootPrintPresenter getPresenter() {
        return new TripFootPrintPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_refresh_recycleview;
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
        mPresenter.getLanScadeDeailData(area_code,true);


    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getLanScadeDeailData(area_code,false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getLanScadeDeailData(area_code,false);
            }
        });
        //调用RecyclerView#addOnItemTouchListener方法能添加一个RecyclerView.OnItemTouchListener对象
        myRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(mContext, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, TripFootPrintDetailAcitivity.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mDatas.get(position/4).getGoto_url());
                    intent.putExtras(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

        }));
    }
            @Override
            public void getFootPrinteData(List<TripFootPrintEntity> data) {
                mDatas.clear();
                mDatas.addAll(data);
                for (TripFootPrintEntity tripStrategyEntity : data) {
                    SingleLayoutHelper item_head_Help = new SingleLayoutHelper();
                    TripFootPrintItemHeadAdapter item_head_Adapter = new TripFootPrintItemHeadAdapter(mContext, item_head_Help, tripStrategyEntity.getPrintItemHeadInfoEntity());
                    adapters.addAdapter(item_head_Adapter);

                    SingleLayoutHelper titleHelp = new SingleLayoutHelper();
                    TripStrategyTitleAdapter titleAdapter = new TripStrategyTitleAdapter(mContext, titleHelp, tripStrategyEntity.getContent());
                    adapters.addAdapter(titleAdapter);


                    GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
                    TipStratygyImgAdapter imgAdapter = new TipStratygyImgAdapter(mContext, gridLayoutHelper, tripStrategyEntity.getImgInfoEntityList());
                    adapters.addAdapter(imgAdapter);

                    SingleLayoutHelper item_end_Help = new SingleLayoutHelper();
                    TripFootPrintItemEndAdapter item_end_Adapter = new TripFootPrintItemEndAdapter(mContext, item_end_Help, tripStrategyEntity.getPrintItemEndEntity());
                    adapters.addAdapter(item_end_Adapter);
                }
                adapters.notifyDataSetChanged();
            }
        }
