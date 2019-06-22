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
import com.yl.triplibrary.net.data.mvp.module.TripWenDaEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripWenDaAdapter extends BaseQuickAdapter<TripWenDaEntity, BaseViewHolder> {


    public TripWenDaAdapter(@Nullable List<TripWenDaEntity> data) {
        super(R.layout.item_trip_wenda_layout, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, TripWenDaEntity item) {
        helper.setText(R.id.item_trip_wenda_title, item.getTitle());
        helper.setText(R.id.item_trip_wenda_user_name,item.getUserInfoEntity().getUsername() );
        helper.setText(R.id.item_trip_wenda_content, item.getContent());
        helper.setText(R.id.bottom_left_text,item.getAddress() );
        helper.setText(R.id.bottom_right_text, item.getOther_desc());
        ImageView itemRankImg=helper.getView(R.id.item_trip_wenda_user_avator);
        if (item.getUserInfoEntity().getUseravator().getImg_url()!=null&&!"".equals(item.getUserInfoEntity().getUseravator().getImg_url())){
            GlideUrl cookie = new GlideUrl(item.getUserInfoEntity().getUseravator().getImg_url(), new Headers() {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> head=new HashMap<>();
                    head.put("Referer", item.getUserInfoEntity().getUseravator().getSource_url());
                    return head;
                }
            });
            Glide.with(mContext).load(cookie).into(itemRankImg);
        }



    }

}
