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
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailHeadEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LanScadeDetailBodyImgAdapter extends DelegateAdapter.Adapter<LanScadeDetailBodyImgAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<ImgInfoEntity> mDatas;


    public LanScadeDetailBodyImgAdapter(Context mContext, LayoutHelper mHelper, List<ImgInfoEntity> mDatas) {
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
                .inflate(R.layout.item_lanscade_head_img_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (mDatas.get(position).getImg_url()!=null&&!"".equals(mDatas.get(position).getImg_url())){
            GlideUrl cookie = new GlideUrl(mDatas.get(position).getImg_url(), new Headers() {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> head=new HashMap<>();
                    head.put("Referer", mDatas.get(position).getSource_url());
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
        return mDatas.size();
    }



    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {
        private ImageView headImg;


        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            headImg = (ImageView)itemView. findViewById(R.id.head_img);

        }
    }
}
