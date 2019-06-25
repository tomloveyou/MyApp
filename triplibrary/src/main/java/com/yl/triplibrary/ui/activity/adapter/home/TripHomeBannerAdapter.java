package com.yl.triplibrary.ui.activity.adapter.home;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.listener.OnBannerClickListener;
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.module.ImaInfoTitleEntity;
import com.yl.triplibrary.ui.activity.TripLineDetailAcitivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TripHomeBannerAdapter extends DelegateAdapter.Adapter<TripHomeBannerAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<ImaInfoTitleEntity> mDatas=new ArrayList<>();
    private List<String> mTitles=new ArrayList<>();
    public TripHomeBannerAdapter(Context mContext, LayoutHelper mHelper, List<ImaInfoTitleEntity> mDatas) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.mDatas = mDatas;
        for (ImaInfoTitleEntity titleEntity:mDatas){
            mTitles.add(titleEntity.getTitle());
        }
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
        holder.banenr.setPages(mDatas, new BannerViewHolder<ImaInfoTitleEntity>() {
            private ImageView imageView;
            @Override
            public View createView(Context context) {
                return imageView=new ImageView(context);
            }

            @Override
            public void onBind(Context context, int position, ImaInfoTitleEntity data) {
                if (data.getImg_url()!=null&!"".equals(data.getImg_url())){//注意：头像url为空时会崩溃
                    GlideUrl cookie = new GlideUrl(data.getImg_url(), new Headers() {
                        @Override
                        public Map<String, String> getHeaders() {
                            Map<String, String> head=new HashMap<>();
                            head.put("Referer", data.getSource_url());
                            return head;
                        }
                    });
                    Glide.with(mContext).load(cookie).into(imageView);
                }
            }
        });
        holder.banenr.setBannerTitles(mTitles);
        holder.banenr.start();
        holder.banenr.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onBannerClick(List datas, int position) {
                Intent intent = new Intent(mContext, TripLineDetailAcitivity.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", mDatas.get(position).getGoto_ur());
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
            banenr=itemView.findViewById(R.id.item_trip_home_banner);

        }
    }
}
