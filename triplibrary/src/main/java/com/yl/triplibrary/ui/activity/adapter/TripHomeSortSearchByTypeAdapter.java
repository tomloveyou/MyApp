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
import com.yl.triplibrary.net.data.mvp.module.ImaInfoTitleEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripHomeSortSearchByTypeAdapter extends BaseQuickAdapter<ImaInfoTitleEntity, BaseViewHolder> {


    public TripHomeSortSearchByTypeAdapter(@Nullable List<ImaInfoTitleEntity> data) {
        super(android.R.layout.simple_list_item_1, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ImaInfoTitleEntity item) {
        helper.setText(android.R.id.text1, item.getTitle());

    }

}
