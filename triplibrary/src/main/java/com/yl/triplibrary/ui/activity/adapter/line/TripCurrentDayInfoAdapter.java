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
import com.yl.triplibrary.net.data.mvp.module.TripCurrentDayInfoEntity;

import java.util.List;


public class TripCurrentDayInfoAdapter extends DelegateAdapter.Adapter<TripCurrentDayInfoAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private TripCurrentDayInfoEntity preBookInfoEntityList;


    public TripCurrentDayInfoAdapter(Context mContext, LayoutHelper mHelper, TripCurrentDayInfoEntity preBookInfoEntityList) {
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
                .inflate(R.layout.item_trip_current_dayinfo_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (preBookInfoEntityList != null) {
            holder.itemTripCurrentDayInfoTitle.setText(preBookInfoEntityList.getTrip_current_day_info_title());
            holder.itemTripCurrentDayInfoDining.setText(preBookInfoEntityList.getTrip_current_day_info_dining());
            holder.itemTripCurrentDayInfoAccommodation.setText(preBookInfoEntityList.getTrip_current_day_info_accommodation());
            holder.itemTripCurrentDayInfoDrive.setText(preBookInfoEntityList.getTrip_current_day_info_drive());
            holder.itemTripCurrentDayInfoDesc.setText(preBookInfoEntityList.getTrip_current_day_info_desc());

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
        private TextView itemTripCurrentDayInfoTitle;
        private TextView itemTripCurrentDayInfoDining;
        private TextView itemTripCurrentDayInfoAccommodation;
        private TextView itemTripCurrentDayInfoDrive;
        private TextView itemTripCurrentDayInfoDesc;


        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemTripCurrentDayInfoTitle = (TextView) itemView.findViewById(R.id.item_trip_current_day_info_title);
            itemTripCurrentDayInfoDining = (TextView) itemView.findViewById(R.id.item_trip_current_day_info_dining);
            itemTripCurrentDayInfoAccommodation = (TextView) itemView.findViewById(R.id.item_trip_current_day_info_accommodation);
            itemTripCurrentDayInfoDrive = (TextView) itemView.findViewById(R.id.item_trip_current_day_info_drive);
            itemTripCurrentDayInfoDesc = (TextView) itemView.findViewById(R.id.item_trip_current_day_info_desc);

        }
    }
}
