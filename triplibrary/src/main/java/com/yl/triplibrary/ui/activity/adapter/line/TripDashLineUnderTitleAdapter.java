package com.yl.triplibrary.ui.activity.adapter.line;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;


public class TripDashLineUnderTitleAdapter extends DelegateAdapter.Adapter<TripDashLineUnderTitleAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private String title;


    public TripDashLineUnderTitleAdapter(Context mContext, LayoutHelper mHelper, String title) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_dashline_under_title_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (title!=null){
            holder.headTitle.setText(title);
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

        private TextView headTitle;


        public RecyclerViewItemHolder(View itemView) {
            super(itemView);

            headTitle = (TextView) itemView. findViewById(R.id.lanscade_detail_title);

        }
    }
}
