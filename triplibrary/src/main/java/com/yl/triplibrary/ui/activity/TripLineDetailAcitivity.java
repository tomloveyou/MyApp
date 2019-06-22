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
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripFootPrintDetailContract;
import com.yl.triplibrary.net.data.mvp.contract.TripLineDetailContract;
import com.yl.triplibrary.net.data.mvp.module.TripCurrentDayItemInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintDetaiEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLinelDetailMainEntity;
import com.yl.triplibrary.net.data.mvp.module.TripTitleContentImgEntity;
import com.yl.triplibrary.net.data.mvp.presenter.TripFootPrintDetailPresenter;
import com.yl.triplibrary.net.data.mvp.presenter.TripLineDetailPresenter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailBodyImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailBodyTitleAdapter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeInfoAdapter;
import com.yl.triplibrary.ui.activity.adapter.TipStratygyImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripFootPrintItemEndAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripFootPrintItemHeadAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripSingleImgAdapter;
import com.yl.triplibrary.ui.activity.adapter.TripStrategyTitleAdapter;
import com.yl.triplibrary.ui.activity.adapter.line.TripClubInfoAdapter;
import com.yl.triplibrary.ui.activity.adapter.line.TripCurrentDayInfoAdapter;
import com.yl.triplibrary.ui.activity.adapter.line.TripDashLineUnderTitleAdapter;
import com.yl.triplibrary.ui.activity.adapter.line.TripLinelDetailHeadAdapter;
import com.yl.triplibrary.ui.activity.adapter.line.TripLinelDetailPreBookAdapter;
import com.yl.triplibrary.ui.activity.adapter.line.TripNomalTitleAdapter;
import com.yl.triplibrary.ui.activity.adapter.line.TripProfileAdapter;
import com.yl.triplibrary.ui.widget.RecyclerViewClickListener;

public class TripLineDetailAcitivity extends BaseTitleBarActivity<TripLineDetailPresenter> implements TripLineDetailContract.TripLineDetailView {
    private RecyclerView myRecyclerView;
    private DelegateAdapter adapters;
    private String url = "";
    private TripLinelDetailMainEntity mDatas;

    @Override
    public TripLineDetailPresenter getPresenter() {
        return new TripLineDetailPresenter(this);
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
        setTitle("路线详情");
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
//                if (adapters.findAdapterByPosition(position).second instanceof TripStrategyTitleAdapter || adapters.findAdapterByPosition(position).second instanceof TipStratygyImgAdapter) {
//                    Intent intent = new Intent(TripLineDetailAcitivity.this, TripLineDetailAcitivity.class);
//                    try {
//                        Bundle bundle = new Bundle();
//                        int dd = position / (mDatas.getImgInfoEntityList().size() + 5);
//                        bundle.putString("url", mDatas.getFootPrintEntityList().get(dd).getGoto_url());
//                        intent.putExtras(bundle);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    startActivity(intent);
//                } else if (adapters.findAdapterByPosition(position).second instanceof LanScadeInfoAdapter) {
//                    if (view.getId() == R.id.item_landscade_address) {//点击地址
//                        Intent intent = new Intent(TripLineDetailAcitivity.this, MytripActivity.class);
//                        try {
//                            Bundle bundle = new Bundle();
//                            bundle.putString("url", mDatas.getLanScadeInfoEntity().getLanScadSimpleAddress_goto_url());
//                            intent.putExtras(bundle);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        startActivity(intent);
//                    } else {//点击景区item
//                        Intent intent = new Intent(TripLineDetailAcitivity.this, LanscadeDetailActivity.class);
//                        try {
//                            Bundle bundle = new Bundle();
//                            bundle.putString("url", mDatas.getLanScadeInfoEntity().getImgInfoEntity().getGoto_ur());
//                            intent.putExtras(bundle);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        startActivity(intent);
//                    }
//
//                }


            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

        }));
    }



    @Override
    public void getTripLineDetailData(TripLinelDetailMainEntity data) {
        mDatas = data;
        /*添加headadapter*/
        if (data.getTitleHeadEntity()!=null){
            SingleLayoutHelper detailhead_Help = new SingleLayoutHelper();
            TripLinelDetailHeadAdapter detail_head_Adapter = new TripLinelDetailHeadAdapter(this, detailhead_Help, data.getTitleHeadEntity());
            adapters.addAdapter(detail_head_Adapter);
        }
        /*添加预定信息Adapter*/
        if (data.getPreBookInfoEntityList()!=null){
            LinearLayoutHelper pre_book_Help = new LinearLayoutHelper();
            TripLinelDetailPreBookAdapter pre_book__Adapter = new TripLinelDetailPreBookAdapter(this, pre_book_Help, data.getPreBookInfoEntityList());
            adapters.addAdapter(pre_book__Adapter);
        }

        /*添加旅游俱乐部adapter*/
        if (data.getTripClubEntity()!=null){
            SingleLayoutHelper trip_club_Help = new SingleLayoutHelper();
            TripClubInfoAdapter trip_club__Adapter = new TripClubInfoAdapter(this, trip_club_Help, data.getTripClubEntity());
            adapters.addAdapter(trip_club__Adapter);
        }
        /*添加路线特色标题*/
        SingleLayoutHelper trip_rute_features_title_Help = new SingleLayoutHelper();
        LanScadeDetailBodyTitleAdapter trip_rute_features_title_adapter = new LanScadeDetailBodyTitleAdapter(this, trip_rute_features_title_Help, "路线特色");
        adapters.addAdapter(trip_rute_features_title_adapter);
        /*添加路线特色内容*/
        if (data.getTrip_line_features()!=null){
            SingleLayoutHelper trip_rute_features_content_Help = new SingleLayoutHelper();
            TripStrategyTitleAdapter trip_rute_features_content_adapter = new TripStrategyTitleAdapter(this, trip_rute_features_content_Help, data.getTrip_line_features());
            adapters.addAdapter(trip_rute_features_content_adapter);
        }
        /*添加行程概况标题*/
        SingleLayoutHelper trip_profile_title_Help = new SingleLayoutHelper();
        LanScadeDetailBodyTitleAdapter trip_profilee_adapter = new LanScadeDetailBodyTitleAdapter(this, trip_profile_title_Help, "行程概况");
        adapters.addAdapter(trip_profilee_adapter);
        /*添加行程概况列表Adapter*/
        if (data.getTripProfileEntities()!=null){
            LinearLayoutHelper trip_profile_list_Help = new LinearLayoutHelper();
            TripProfileAdapter trip_profile_list_adapter = new TripProfileAdapter(this, trip_profile_list_Help, data.getTripProfileEntities());
            adapters.addAdapter(trip_profile_list_adapter);
        }

        /*添加行程安排详情标题*/
        SingleLayoutHelper trip_profile_detaile_title_Help = new SingleLayoutHelper();
        LanScadeDetailBodyTitleAdapter trip_profile_detaile_adapter = new LanScadeDetailBodyTitleAdapter(this, trip_profile_detaile_title_Help, "行程安排");
        adapters.addAdapter(trip_profile_detaile_adapter);
        /*添加行程安排详情列表Adapter*/
        if (data.getTripCurrentDayItemInfoEntityList()!=null)
        for (TripCurrentDayItemInfoEntity itemInfoEntity:data.getTripCurrentDayItemInfoEntityList()){
            /*添加行程概况Adapter*/
            if (itemInfoEntity.getCurrentDayInfoEntity()!=null){
                SingleLayoutHelper trip_current_day_normal_Help = new SingleLayoutHelper();
                TripCurrentDayInfoAdapter trip_current_day_normal_adapter = new TripCurrentDayInfoAdapter(this, trip_current_day_normal_Help, itemInfoEntity.getCurrentDayInfoEntity());
                adapters.addAdapter(trip_current_day_normal_adapter);
            }

            if (itemInfoEntity.getTripTitleContentImgEntity()!=null)
            for (TripTitleContentImgEntity contentImgEntity:itemInfoEntity.getTripTitleContentImgEntity()){
                /*添加风景标题*/
                if (contentImgEntity.getTitle()!=null&&!"".equals(contentImgEntity.getTitle())){
                    SingleLayoutHelper trip_scence_title_Help = new SingleLayoutHelper();
                    TripDashLineUnderTitleAdapter trip_scence_title_adapter = new TripDashLineUnderTitleAdapter(this, trip_scence_title_Help, contentImgEntity.getTitle());
                    adapters.addAdapter(trip_scence_title_adapter);
                }
                /*添加风景内容*/
                if (contentImgEntity.getContent()!=null&&!"".equals(contentImgEntity.getContent())){
                    SingleLayoutHelper trip_scence_content_Help = new SingleLayoutHelper();
                    TripStrategyTitleAdapter trip_scence_content_adapter = new TripStrategyTitleAdapter(this, trip_scence_content_Help, contentImgEntity.getContent());
                    adapters.addAdapter(trip_scence_content_adapter);
                }
                /*添加行程图片Adapter*/
                LinearLayoutHelper trip_current_day_scence_img_Help = new LinearLayoutHelper();
                LanScadeDetailBodyImgAdapter trip_current_day_scence_img_adapter = new LanScadeDetailBodyImgAdapter(this, trip_current_day_scence_img_Help, contentImgEntity.getImgInfoEntityList());
                adapters.addAdapter(trip_current_day_scence_img_adapter);
            }



        }
        /*添加费用说明标题*/
        SingleLayoutHelper trip_cost_title_Help = new SingleLayoutHelper();
        TripStrategyTitleAdapter trip_cost_title_adapter = new TripStrategyTitleAdapter(this, trip_cost_title_Help, "费用说明");
        adapters.addAdapter(trip_cost_title_adapter);
        /*添加费用说明内容*/
        if (data.getCost_info_list()!=null){
            LinearLayoutHelper trip_cost_list_Help = new LinearLayoutHelper();
            TripNomalTitleAdapter trip_cost_list_adapter = new TripNomalTitleAdapter(this, trip_cost_list_Help, data.getCost_info_list());
            adapters.addAdapter(trip_cost_list_adapter);
        }
        /*添加费用说明标题*/
        SingleLayoutHelper trip_tip_title_Help = new SingleLayoutHelper();
        TripStrategyTitleAdapter trip_tip_title_adapter = new TripStrategyTitleAdapter(this, trip_tip_title_Help, "路线须知");
        adapters.addAdapter(trip_tip_title_adapter);

        /*添加费用说明内容*/
        if (data.getTip_info_list()!=null){
            LinearLayoutHelper trip_tip_list_Help = new LinearLayoutHelper();
            TripNomalTitleAdapter trip_tip_list_adapter = new TripNomalTitleAdapter(this, trip_tip_list_Help, data.getTip_info_list());
            adapters.addAdapter(trip_tip_list_adapter);
            adapters.notifyDataSetChanged();
        }

    }
}
