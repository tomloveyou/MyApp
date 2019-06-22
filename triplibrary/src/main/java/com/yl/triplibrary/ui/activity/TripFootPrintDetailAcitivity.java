package com.yl.triplibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.constant.Constant;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripFootPrintDetailContract;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintDetaiEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.presenter.TripFootPrintDetailPresenter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailBodyImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailBodyTitleAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailHeadAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeInfoAdapter;
import com.yl.triplibrary.ui.activity.adapter.TipStratygyImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripFootPrintItemEndAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripFootPrintItemHeadAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripStrategyTitleAdapter;
import com.yl.triplibrary.ui.widget.RecyclerViewClickListener;

public class TripFootPrintDetailAcitivity extends BaseTitleBarActivity<TripFootPrintDetailPresenter> implements TripFootPrintDetailContract.TripFootPrintDetailView {
    private RecyclerView myRecyclerView;
    private DelegateAdapter adapters;
    private String url = "";
    private TripFootPrintDetaiEntity mDatas;

    @Override
    public TripFootPrintDetailPresenter getPresenter() {
        return new TripFootPrintDetailPresenter(this);
    }

    @Override
    public void getExtra() {
        super.getExtra();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString("url", "http://m.dazijia.com/zuji/14980.html");
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_norefresh_recycleview_layout;
    }

    @Override
    protected void init() {
        setTitle("足迹详情");
        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        myRecyclerView.setLayoutManager(layoutManager);

        adapters = new DelegateAdapter(layoutManager, false);
        myRecyclerView.setAdapter(adapters);
        mPresenter.getLanScadeDeailData(true, url);

    }

    @Override
    protected void setListener() {
        //调用RecyclerView#addOnItemTouchListener方法能添加一个RecyclerView.OnItemTouchListener对象
        myRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (adapters.findAdapterByPosition(position).second instanceof TripStrategyTitleAdapter || adapters.findAdapterByPosition(position).second instanceof TipStratygyImgAdapter) {
                    Intent intent = new Intent(TripFootPrintDetailAcitivity.this, TripFootPrintDetailAcitivity.class);
                    try {
                        Bundle bundle = new Bundle();
                        int dd = position / (mDatas.getImgInfoEntityList().size() + 5);
                        bundle.putString("url", mDatas.getFootPrintEntityList().get(dd).getGoto_url());
                        intent.putExtras(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                }else if (adapters.findAdapterByPosition(position).second instanceof LanScadeInfoAdapter){
                    if (view.getId()==R.id.item_landscade_address){//点击地址
                        Intent intent=new Intent(TripFootPrintDetailAcitivity.this, MytripActivity.class);
                        try {
                            Bundle bundle=new Bundle();
                            bundle.putString("url", mDatas.getLanScadeInfoEntity().getLanScadSimpleAddress_goto_url());
                            intent.putExtras(bundle);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                       startActivity(intent);
                    }else {//点击景区item
                        Intent intent=new Intent(TripFootPrintDetailAcitivity.this, LanscadeDetailActivity.class);
                        try {
                            Bundle bundle=new Bundle();
                            bundle.putString("url", mDatas.getLanScadeInfoEntity().getImgInfoEntity().getGoto_ur());
                            intent.putExtras(bundle);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        startActivity(intent);
                    }

                }




            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

        }));
    }

    @Override
    public void getFootPrinteDetailData(TripFootPrintDetaiEntity data) {
        mDatas = data;

        SingleLayoutHelper detailhead_Help = new SingleLayoutHelper();
        TripFootPrintItemHeadAdapter detail_head_Adapter = new TripFootPrintItemHeadAdapter(this, detailhead_Help, data.getHeadEntity());
        adapters.addAdapter(detail_head_Adapter);

        LinearLayoutHelper detail_img_Help = new LinearLayoutHelper();
        LanScadeDetailBodyImgAdapter detail_img_Adapter = new LanScadeDetailBodyImgAdapter(this, detail_img_Help, data.getImgInfoEntityList());
        adapters.addAdapter(detail_img_Adapter);

        SingleLayoutHelper detail_landscade_Help = new SingleLayoutHelper();
        LanScadeInfoAdapter detail_landscade_Adapter = new LanScadeInfoAdapter(this, detail_landscade_Help, data.getLanScadeInfoEntity());
        adapters.addAdapter(detail_landscade_Adapter);

        SingleLayoutHelper bottom_title_Help = new SingleLayoutHelper();
        LanScadeDetailBodyTitleAdapter bottom_title_adapter = new LanScadeDetailBodyTitleAdapter(this, bottom_title_Help, "相关足迹");
        adapters.addAdapter(bottom_title_adapter);
        for (TripFootPrintEntity tripStrategyEntity : data.getFootPrintEntityList()) {
            SingleLayoutHelper item_head_Help = new SingleLayoutHelper();
            TripFootPrintItemHeadAdapter item_head_Adapter = new TripFootPrintItemHeadAdapter(this, item_head_Help, tripStrategyEntity.getPrintItemHeadInfoEntity());
            adapters.addAdapter(item_head_Adapter);

            SingleLayoutHelper titleHelp = new SingleLayoutHelper();
            TripStrategyTitleAdapter titleAdapter = new TripStrategyTitleAdapter(this, titleHelp, tripStrategyEntity.getContent());
            adapters.addAdapter(titleAdapter);

            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
            TipStratygyImgAdapter imgAdapter = new TipStratygyImgAdapter(this, gridLayoutHelper, tripStrategyEntity.getImgInfoEntityList());
            adapters.addAdapter(imgAdapter);

            SingleLayoutHelper item_end_Help = new SingleLayoutHelper();
            TripFootPrintItemEndAdapter item_end_Adapter = new TripFootPrintItemEndAdapter(this, item_head_Help, tripStrategyEntity.getPrintItemEndEntity());
            adapters.addAdapter(item_end_Adapter);
        }
        adapters.notifyDataSetChanged();
    }
}
