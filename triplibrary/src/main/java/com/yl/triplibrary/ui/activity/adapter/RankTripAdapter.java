package com.yl.triplibrary.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;

import java.util.HashMap;
import java.util.Map;

public class RankTripAdapter extends LoadMoreRecycleAdapter<RankTripZone, RankTripAdapter.ViewHolder> {



    public RankTripAdapter(Context mContext) {
        super(mContext);
        removeHeaderView(0X666);
        removeFooterView(0X11);

    }

    @Override
    public ViewHolder onCreateContentView(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rank_trip_layout, parent, false));
    }

    @Override
    public void onBindView(ViewHolder viewHolder, int realPosition) {
        viewHolder.setData(mData.get(realPosition), realPosition);
    }

    private void initView() {

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemRankTitle;
        private TextView itemRankDesc;
        private ImageView itemRankImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRankTitle = (TextView) itemView.findViewById(R.id.item_rank_title);
            itemRankDesc = (TextView)  itemView.findViewById(R.id.item_rank_desc);
            itemRankImg = (ImageView)  itemView.findViewById(R.id.item_rank_img);
        }

        public void setData(RankTripZone data, int position) {
            itemRankTitle.setText(data.getTitle());
            itemRankDesc.setText(data.getContent());
            GlideUrl cookie = new GlideUrl(data.getOriginal_img(), new Headers() {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> head=new HashMap<>();
                    head.put("Referer", data.getImg_head());
                    return head;
                }
            });

            Glide.with(mContext).load(cookie).into(itemRankImg);

        }

    }
}
