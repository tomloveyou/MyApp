package com.yl.triplibrary.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.standards.library.base.BasePresenter;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.LanScadeDeailContract;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailEntity;
import com.yl.triplibrary.net.data.mvp.presenter.LanScadeDetailPresenter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailBodyImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailBodyTitleAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailHeadAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailRecomentAdapter;

public class LanscadeDetailActivity extends BaseTitleBarActivity<LanScadeDetailPresenter> implements LanScadeDeailContract.LanScadeDeailView {
    private RecyclerView myRecyclerView;
    private DelegateAdapter adapters;
    private LanScadeDetailHeadAdapter headAdapter;
    private String url="";

    @Override
    public LanScadeDetailPresenter getPresenter() {
        return new LanScadeDetailPresenter(this);
    }

    @Override
    public void getExtra() {
        super.getExtra();
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            url=bundle.getString("url","http://m.dazijia.com/jingdian/699");
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_norefresh_recycleview_layout;
    }

    @Override
    protected void init() {

        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        myRecyclerView.setLayoutManager(layoutManager);

        adapters = new DelegateAdapter(layoutManager, false);

        //banner
        SingleLayoutHelper bannerHelp = new SingleLayoutHelper();
        headAdapter = new LanScadeDetailHeadAdapter(this, bannerHelp);
        adapters.addAdapter(headAdapter);
        myRecyclerView.setAdapter(adapters);
        mPresenter.getLanScadeDeailData(url);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void getLanScadeDeailData(LanScadeDetailEntity data) {
        headAdapter.setHeadEntity(data.getHeadEntity());
        setTitle(data.getHeadEntity().getTitle()+"详情");
        headAdapter.notifyDataSetChanged();
        if (data.getDetailBodyEntity() != null && data.getDetailBodyEntity().size() > 0) {
            for (int i = 0; i < data.getDetailBodyEntity().size(); i++) {
                SingleLayoutHelper titleHelp = new SingleLayoutHelper();
                LanScadeDetailBodyTitleAdapter titleadapter = new LanScadeDetailBodyTitleAdapter(LanscadeDetailActivity.this, titleHelp, data.getDetailBodyEntity().get(i).getBody_title());
                adapters.addAdapter(titleadapter);
                LinearLayoutHelper bodylistHelp=new LinearLayoutHelper();
                LanScadeDetailBodyImgAdapter bodyImgAdapter=new LanScadeDetailBodyImgAdapter(LanscadeDetailActivity.this, bodylistHelp, data.getDetailBodyEntity().get(i).getImgInfoEntityList());
                adapters.addAdapter(bodyImgAdapter);

            }

        }
        SingleLayoutHelper titleHelp = new SingleLayoutHelper();
        LanScadeDetailBodyTitleAdapter titleadapter = new LanScadeDetailBodyTitleAdapter(LanscadeDetailActivity.this, titleHelp, data.getFoot_recommnet_title());
        adapters.addAdapter(titleadapter);
        LinearLayoutHelper footlistHelp=new LinearLayoutHelper();
        LanScadeDetailRecomentAdapter bodyImgAdapter=new LanScadeDetailRecomentAdapter(LanscadeDetailActivity.this, footlistHelp, data.getRecomentEntityList());
        adapters.addAdapter(bodyImgAdapter);
        adapters.notifyDataSetChanged();
    }
}
