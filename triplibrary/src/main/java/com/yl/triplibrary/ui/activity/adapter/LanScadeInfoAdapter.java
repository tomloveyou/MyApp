package com.yl.triplibrary.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.module.LanScadeInfoEntity;
import com.yl.triplibrary.ui.activity.LanscadeDetailActivity;
import com.yl.triplibrary.ui.activity.MytripActivity;

import java.util.HashMap;
import java.util.Map;


public class LanScadeInfoAdapter extends DelegateAdapter.Adapter<LanScadeInfoAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private LanScadeInfoEntity lanScadeInfoEntity;



    public LanScadeInfoAdapter(Context mContext, LayoutHelper mHelper, LanScadeInfoEntity lanScadeInfoEntity) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.lanScadeInfoEntity = lanScadeInfoEntity;
    }

    public void setTitle(LanScadeInfoEntity lanScadeInfoEntity) {
        this.lanScadeInfoEntity = lanScadeInfoEntity;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_lanscade_info_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (lanScadeInfoEntity != null) {
            holder.itemLandscadeName.setText(lanScadeInfoEntity.getLanScadeName());
            holder.itemLandscadeDesc.setText(lanScadeInfoEntity.getLanScadeDesc());
            holder.itemLandscadeTime.setText(lanScadeInfoEntity.getLanScadInfoTime());
            holder.itemLandscadeAddress.setText(lanScadeInfoEntity.getLanScadSimpleAddress());
            if (lanScadeInfoEntity.getImgInfoEntity().getImg_url()!=null&!"".equals(lanScadeInfoEntity.getImgInfoEntity().getImg_url())){//注意：头像url为空时会崩溃
                GlideUrl cookie = new GlideUrl(lanScadeInfoEntity.getImgInfoEntity().getImg_url(), new Headers() {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> head=new HashMap<>();
                        head.put("Referer", lanScadeInfoEntity.getImgInfoEntity().getSource_url());
                        return head;
                    }
                });
                Glide.with(mContext).load(cookie).into(holder.itemLandscadeImg);
            }

        }



    }

    @Override
    public int getItemViewType(int position) {
        return Constant.viewType.typePlus;
    }

    @Override
    public int getItemCount() {
        return 1;
    }




    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        private ImageView itemLandscadeImg;
        private TextView itemLandscadeName;
        private TextView itemLandscadeDesc;
        private TextView itemLandscadeTime;
        private TextView itemLandscadeAddress;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemLandscadeImg = (ImageView)itemView. findViewById(R.id.item_landscade_img);
            itemLandscadeName = (TextView)itemView. findViewById(R.id.item_landscade_name);
            itemLandscadeDesc = (TextView)itemView. findViewById(R.id.item_landscade_desc);
            itemLandscadeTime = (TextView)itemView. findViewById(R.id.item_landscade_time);
            itemLandscadeAddress = (TextView)itemView. findViewById(R.id.item_landscade_address);

        }
    }
}
