package com.yl.triplibrary.ui.activity.adapter.home;

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
import com.yl.triplibrary.net.data.mvp.module.TripHomeSortConoditionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TripHomeSortSeachAdapter extends DelegateAdapter.Adapter<TripHomeSortSeachAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private TripHomeSortConoditionEntity mDatas;



    public TripHomeSortSeachAdapter(Context mContext, LayoutHelper mHelper, TripHomeSortConoditionEntity mDatas) {
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
                .inflate(R.layout.item_triphome_gv_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {

        if (mDatas != null) {


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return Constant.viewType.typeGv;
    }

    @Override
    public int getItemCount() {
        return 1;
    }




    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {
        private ImageView itemTriphomeGvImg;
        private TextView itemTriphomeGvTitle;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemTriphomeGvImg = (ImageView) itemView.findViewById(R.id.item_triphome_gv_img);
            itemTriphomeGvTitle = (TextView) itemView.findViewById(R.id.item_triphome_gv_title);
        }
    }
}
