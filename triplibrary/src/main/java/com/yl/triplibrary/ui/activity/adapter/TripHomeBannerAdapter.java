package com.yl.triplibrary.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.listener.OnBannerClickListener;
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.module.ImaInfoTitleEntity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class TripHomeBannerAdapter extends DelegateAdapter.Adapter<TripHomeBannerAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<ImaInfoTitleEntity> mDatas;

    public TripHomeBannerAdapter(Context mContext, LayoutHelper mHelper, List<ImaInfoTitleEntity> mDatas) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.mDatas = mDatas;
    }


    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerViewItemHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.banenr.startAutoPlay();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerViewItemHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.banenr.stopAutoPlay();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_triphome_banner_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        holder.banenr.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        holder.banenr.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE);
        holder.banenr.setAutoPlay(true);
        LinearLayout a=holder.banenr.findViewById(R.id.circleIndicator);
        holder.banenr.setPages(mDatas, new BannerViewHolder() {
            @Override
            public View createView(Context context) {
                return null;
            }

            @Override
            public void onBind(Context context, int position, Object data) {

            }
        });
        holder.banenr.start();
        holder.banenr.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onBannerClick(List datas, int position) {

            }

        });
    }

    @Override
    public int getItemViewType(int position) {
        return Constant.viewType.typeBanner;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {



        Banner banenr;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);

        }
    }
}
