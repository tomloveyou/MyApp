package com.yl.triplibrary.ui.activity.adapter.home;

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
import com.yl.triplibrary.net.data.mvp.module.TripHomeRecommnetEntity;
import com.yl.triplibrary.ui.activity.TripLineDetailAcitivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TripHomeRecomentAdapter extends DelegateAdapter.Adapter<TripHomeRecomentAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<TripHomeRecommnetEntity> mDatas;



    public TripHomeRecomentAdapter(Context mContext, LayoutHelper mHelper, List<TripHomeRecommnetEntity> mDatas) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.mDatas = mDatas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_rank_trip_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        TripHomeRecommnetEntity rankTripZone=mDatas.get(position);
        if (rankTripZone!=null){
            holder.itemRankTitle.setText(mDatas.get(position).getTitle());
            holder.itemRankDesc.setText(mDatas.get(position).getContent());
            if (rankTripZone.getImgInfoEntity().getImg_url()!=null&!"".equals(rankTripZone.getImgInfoEntity().getImg_url())){//注意：头像url为空时会崩溃
                GlideUrl cookie = new GlideUrl(rankTripZone.getImgInfoEntity().getImg_url(), new Headers() {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> head=new HashMap<>();
                        head.put("Referer", rankTripZone.getImgInfoEntity().getSource_url());
                        return head;
                    }
                });
                Glide.with(mContext).load(cookie).into(holder.itemRankImg);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TripLineDetailAcitivity.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mDatas.get(position).getImgInfoEntity().getGoto_ur());
                    intent.putExtras(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mContext. startActivity(intent);
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return Constant.viewType.typeList;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }




    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {
        private TextView itemRankTitle;
        private TextView itemRankDesc;
        private ImageView itemRankImg;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemRankTitle = (TextView)itemView. findViewById(R.id.item_rank_title);
            itemRankDesc = (TextView)itemView. findViewById(R.id.item_rank_desc);
            itemRankImg = (ImageView) itemView.findViewById(R.id.item_rank_img);
        }
    }
}
