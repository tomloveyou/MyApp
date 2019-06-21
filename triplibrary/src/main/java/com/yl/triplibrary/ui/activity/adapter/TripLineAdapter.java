package com.yl.triplibrary.ui.activity.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.adapter.BaseViewHolder;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripLineAdapter extends BaseQuickAdapter<TripLineEntity, BaseViewHolder> {


    public TripLineAdapter(@Nullable List<TripLineEntity> data) {
        super(R.layout.item_rip_line_layout, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, TripLineEntity item) {
        if (item!=null){
            helper.setText(R.id.item_trip_line_left_text, item.getPeople_price_unit_info());
            helper.setText(R.id.item_trip_line_right_text, item.getGo_start_eral());
            helper.setText(R.id.item_trip_line_bottom_title,item.getTitle() );
            ImageView itemRankImg=helper.getView(R.id.item_trip_line_img);
            GlideUrl cookie = new GlideUrl(item.getImg_url(), new Headers() {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> head=new HashMap<>();
                    head.put("Referer", item.getSource_url());
                    return head;
                }
            });

            Glide.with(mContext).load(cookie).into(itemRankImg);
        }

    }

}
