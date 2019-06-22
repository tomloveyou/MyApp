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
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemEndEntity;


public class TripFootPrintItemEndAdapter extends DelegateAdapter.Adapter<TripFootPrintItemEndAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private TripFootPrintItemEndEntity endEntity;



    public TripFootPrintItemEndAdapter(Context mContext, LayoutHelper mHelper, TripFootPrintItemEndEntity endEntity) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.endEntity = endEntity;
    }

    public void setTitle(String title) {
        this.endEntity = endEntity;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_trip_foot_print_item_end_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (endEntity != null) {
            holder.itemTripFootprintEndAddress.setText(endEntity.getAddress_info());
            holder.itemTripFootprintEndStore.setText(endEntity.getStore_count());
            holder.itemTripFootprintEndTime.setText(endEntity.getTime());
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
        private TextView itemTripFootprintEndAddress;
        private TextView itemTripFootprintEndStore;
        private TextView itemTripFootprintEndTime;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemTripFootprintEndAddress = (TextView)itemView. findViewById(R.id.item_trip_footprint_end_address);
            itemTripFootprintEndStore = (TextView)itemView.  findViewById(R.id.item_trip_footprint_end_store);
            itemTripFootprintEndTime = (TextView)itemView.  findViewById(R.id.item_trip_footprint_end_time);

        }
    }
}
