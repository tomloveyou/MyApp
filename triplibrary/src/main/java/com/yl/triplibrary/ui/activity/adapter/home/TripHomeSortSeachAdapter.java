package com.yl.triplibrary.ui.activity.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.module.TripHomeSortConoditionEntity;
import com.yl.triplibrary.ui.activity.adapter.TripHomeSortSearchByTypeAdapter;

import java.util.ArrayList;


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
                .inflate(R.layout.item_triphome_sortsearch_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        holder.gvFilterName.setLayoutManager(gridLayoutManager);
        TripHomeSortSearchByTypeAdapter typeAdapter = new TripHomeSortSearchByTypeAdapter(new ArrayList<>());
        holder.gvFilterName.setAdapter(typeAdapter);
        holder.llCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeAdapter.setNewData(mDatas.getStart_area());
            }
        });
        holder.llSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeAdapter.setNewData(mDatas.getStart_month());
            }
        });
        holder.llFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeAdapter.setNewData(mDatas.getEnd_area());
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
        private LinearLayout llHeadLayout;
        private LinearLayout llCategory;
        private TextView tvCategoryTitle;
        private ImageView ivCategoryArrow;
        private LinearLayout llSort;
        private TextView tvSortTitle;
        private ImageView ivSortArrow;
        private LinearLayout llFilter;
        private TextView tvFilterTitle;
        private ImageView ivFilterArrow;
        private RecyclerView gvFilterName;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            llHeadLayout = (LinearLayout) itemView.findViewById(R.id.ll_head_layout);
            llCategory = (LinearLayout) itemView.findViewById(R.id.ll_category);
            tvCategoryTitle = (TextView) itemView.findViewById(R.id.tv_category_title);
            ivCategoryArrow = (ImageView) itemView.findViewById(R.id.iv_category_arrow);
            llSort = (LinearLayout) itemView.findViewById(R.id.ll_sort);
            tvSortTitle = (TextView) itemView.findViewById(R.id.tv_sort_title);
            ivSortArrow = (ImageView) itemView.findViewById(R.id.iv_sort_arrow);
            llFilter = (LinearLayout) itemView.findViewById(R.id.ll_filter);
            tvFilterTitle = (TextView) itemView.findViewById(R.id.tv_filter_title);
            ivFilterArrow = (ImageView) itemView.findViewById(R.id.iv_filter_arrow);
            gvFilterName = (RecyclerView) itemView.findViewById(R.id.gv_filter_name);
        }
    }
}
