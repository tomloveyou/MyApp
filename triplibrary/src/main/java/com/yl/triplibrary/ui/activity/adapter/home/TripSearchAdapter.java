package com.yl.triplibrary.ui.activity.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;


public class TripSearchAdapter extends DelegateAdapter.Adapter<TripSearchAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;



    public TripSearchAdapter(Context mContext, LayoutHelper mHelper) {
        this.mContext = mContext;
        this.mHelper = mHelper;

    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_trip_search_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {


    }

    @Override
    public int getItemViewType(int position) {
        return Constant.viewType.typeSearch;
    }

    @Override
    public int getItemCount() {
        return 1;
    }




    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        private EditText itemTripSearchEtText;
        private ImageView itemTripSearchIvSearch;


        public RecyclerViewItemHolder(View itemView) {
            super(itemView);

            itemTripSearchEtText = (EditText)itemView. findViewById(R.id.item_trip_search_et_text);
            itemTripSearchIvSearch = (ImageView) itemView.findViewById(R.id.item_trip_search_iv_search);

        }
    }
}
