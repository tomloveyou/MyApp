package com.yl.triplibrary.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.adapter.BaseViewHolder;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTripAdapter2 extends BaseQuickAdapter<RankTripZone, BaseViewHolder> {


    public RankTripAdapter2( @Nullable List<RankTripZone> data) {
        super(R.layout.item_rank_trip_layout, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, RankTripZone item) {
        helper.setText(R.id.item_rank_title, item.getTitle());
        helper.setText(R.id.item_rank_desc,item.getContent() );
        ImageView itemRankImg=helper.getView(R.id.item_rank_img);
        GlideUrl cookie = new GlideUrl(item.getOriginal_img(), new Headers() {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> head=new HashMap<>();
                head.put("Referer", item.getImg_head());
                return head;
            }
        });

        Glide.with(mContext).load(cookie).into(itemRankImg);
    }


}
