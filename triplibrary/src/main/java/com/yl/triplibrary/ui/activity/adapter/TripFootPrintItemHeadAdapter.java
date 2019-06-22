package com.yl.triplibrary.ui.activity.adapter;

import android.content.Context;
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
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemHeadEntity;

import java.util.HashMap;
import java.util.Map;


public class TripFootPrintItemHeadAdapter extends DelegateAdapter.Adapter<TripFootPrintItemHeadAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private TripFootPrintItemHeadEntity headEntity;



    public TripFootPrintItemHeadAdapter(Context mContext, LayoutHelper mHelper, TripFootPrintItemHeadEntity headEntity) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.headEntity = headEntity;
    }

    public void setTitle(String title) {
        this.headEntity = headEntity;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_trip_foot_print_item_head_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (headEntity != null) {
            holder.itemTripFootprintUserName.setText(headEntity.getUsername());
            holder.itemTripFootprintUserReadcount.setText(headEntity.getReadinfo());
            if (headEntity.getUseravator().getImg_url()!=null&!"".equals(headEntity.getUseravator().getImg_url())){//注意：头像url为空时会崩溃
                GlideUrl cookie = new GlideUrl(headEntity.getUseravator().getImg_url(), new Headers() {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> head=new HashMap<>();
                        head.put("Referer", headEntity.getUseravator().getSource_url());
                        return head;
                    }
                });
                Glide.with(mContext).load(cookie).into(holder.itemTripFootprintUserAvator);
            }

        }


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
        private ImageView itemTripFootprintUserAvator;
        private TextView itemTripFootprintUserName;
        private TextView itemTripFootprintUserReadcount;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemTripFootprintUserAvator = (ImageView)itemView. findViewById(R.id.item_trip_footprint_user_avator);
            itemTripFootprintUserName = (TextView)itemView. findViewById(R.id.item_trip_footprint_user_name);
            itemTripFootprintUserReadcount = (TextView)itemView. findViewById(R.id.item_trip_footprint_user_readcount);

        }
    }
}
