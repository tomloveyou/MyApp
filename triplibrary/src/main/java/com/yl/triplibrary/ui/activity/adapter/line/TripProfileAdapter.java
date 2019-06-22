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
import com.yl.triplibrary.net.data.mvp.module.PreBookInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripProfileEntity;

import java.util.List;


public class TripProfileAdapter extends DelegateAdapter.Adapter<TripProfileAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private List<TripProfileEntity> preBookInfoEntityList;



    public TripProfileAdapter(Context mContext, LayoutHelper mHelper, List<TripProfileEntity> preBookInfoEntityList) {
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
                .inflate(R.layout.item_trip_profile_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (preBookInfoEntityList != null) {
            holder.itemTripLinePrebookName.setText(preBookInfoEntityList.get(position).getLeft_text());
            holder.itemTripLinePrebookValue.setText(preBookInfoEntityList.get(position).getRight_text());
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
            itemTripLinePrebookName = (TextView) itemView.findViewById(R.id.item_tripprofile_name);
            itemTripLinePrebookValue = (TextView) itemView.findViewById(R.id.item_tripprofilek_value);

        }
    }
}
