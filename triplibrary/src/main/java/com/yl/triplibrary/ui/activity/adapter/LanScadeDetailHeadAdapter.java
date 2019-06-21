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
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailHeadEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LanScadeDetailHeadAdapter extends DelegateAdapter.Adapter<LanScadeDetailHeadAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private LanScadeDetailHeadEntity headEntity;


    public LanScadeDetailHeadAdapter(Context mContext, LayoutHelper mHelper) {
        this.mContext = mContext;
        this.mHelper = mHelper;

    }

    public void setHeadEntity(LanScadeDetailHeadEntity headEntity) {
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
                .inflate(R.layout.item_landscade_head_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (headEntity!=null){
            holder.headTitle.setText(headEntity.getTitle());
            holder.headDesc.setText(headEntity.getDesc());
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
        private TextView headDesc;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            headImg = (ImageView)itemView. findViewById(R.id.head_img);
            headTitle = (TextView) itemView. findViewById(R.id.head_title);
            headDesc= (TextView) itemView. findViewById(R.id.head_desc);
        }
    }
}
