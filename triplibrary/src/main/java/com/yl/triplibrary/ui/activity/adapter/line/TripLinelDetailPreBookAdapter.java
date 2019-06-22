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
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.module.PreBookInfoEntity;

import java.util.List;


public class TripLinelDetailPreBookAdapter extends DelegateAdapter.Adapter<TripLinelDetailPreBookAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<PreBookInfoEntity> preBookInfoEntityList;



    public TripLinelDetailPreBookAdapter(Context mContext, LayoutHelper mHelper, List<PreBookInfoEntity> preBookInfoEntityList) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.preBookInfoEntityList = preBookInfoEntityList;

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_trip_line_detail_prebook_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (preBookInfoEntityList != null) {
            holder.itemTripLinePrebookName.setText(preBookInfoEntityList.get(position).getName() + "：");
            holder.itemTripLinePrebookValue.setText(preBookInfoEntityList.get(position).getValues());
        }

    }

    @Override
    public int getItemViewType(int position) {
        return Constant.viewType.typeBanner;
    }

    @Override
    public int getItemCount() {
        return preBookInfoEntityList.size();
    }



    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {
        private TextView itemTripLinePrebookName;
        private TextView itemTripLinePrebookValue;


        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemTripLinePrebookName = (TextView) itemView.findViewById(R.id.item_trip_line_prebook_name);
            itemTripLinePrebookValue = (TextView) itemView.findViewById(R.id.item_trip_line_prebook_value);

        }
    }
}
