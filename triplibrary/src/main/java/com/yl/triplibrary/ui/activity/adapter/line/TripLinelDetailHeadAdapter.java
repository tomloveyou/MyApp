package com.yl.triplibrary.ui.activity.adapter.line;

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
import com.yl.triplibrary.net.data.mvp.module.ImaInfoTitleEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailHeadEntity;

import java.util.HashMap;
import java.util.Map;


public class TripLinelDetailHeadAdapter extends DelegateAdapter.Adapter<TripLinelDetailHeadAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private ImaInfoTitleEntity headEntity;


    public TripLinelDetailHeadAdapter(Context mContext, LayoutHelper mHelper) {
        this.mContext = mContext;
        this.mHelper = mHelper;

    }

    public TripLinelDetailHeadAdapter(Context mContext, LayoutHelper mHelper, ImaInfoTitleEntity headEntity) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.headEntity = headEntity;
    }

    public void setHeadEntity(ImaInfoTitleEntity headEntity) {
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
                .inflate(R.layout.item_trip_line_detail_head_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (headEntity!=null){
            holder.headTitle.setText(headEntity.getTitle());
            GlideUrl cookie = new GlideUrl(headEntity.getImg_url(), new Headers() {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> head=new HashMap<>();
                    head.put("Referer", headEntity.getSource_url());
                    return head;
                }
            });
            Glide.with(mContext).load(cookie).into(holder.headImg);
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
        private ImageView headImg;
        private TextView headTitle;


        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            headImg = (ImageView)itemView. findViewById(R.id.head_img);
            headTitle = (TextView) itemView. findViewById(R.id.head_title);

        }
    }
}
