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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.standards.library.constant.Constant;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.module.TripClubEntity;

import java.util.HashMap;
import java.util.Map;


public class TripClubInfoAdapter extends DelegateAdapter.Adapter<TripClubInfoAdapter.RecyclerViewItemHolder> {

    private Context mContext;
    private LayoutHelper mHelper;
    private TripClubEntity lanScadeInfoEntity;



    public TripClubInfoAdapter(Context mContext, LayoutHelper mHelper, TripClubEntity lanScadeInfoEntity) {
        this.mContext = mContext;
        this.mHelper = mHelper;
        this.lanScadeInfoEntity = lanScadeInfoEntity;
    }

    public void setTitle(TripClubEntity lanScadeInfoEntity) {
        this.lanScadeInfoEntity = lanScadeInfoEntity;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_tripclub_info_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemHolder holder, final int position) {
        if (lanScadeInfoEntity != null) {
            holder.itemTripclubName.setText(lanScadeInfoEntity.getClub_name());
            holder.itemTripclubFocusPeopleCount.setText(lanScadeInfoEntity.getClub_focus_people_count());
            holder.itemTripclubTriplineCount.setText(lanScadeInfoEntity.getClub_tripline_count());
            holder.itemTripclubPeopleCount.setText(lanScadeInfoEntity.getClub_people_count());
            if (lanScadeInfoEntity.getClub_icon().getImg_url() != null & !"".equals(lanScadeInfoEntity.getClub_icon().getImg_url())) {//注意：头像url为空时会崩溃
                GlideUrl cookie = new GlideUrl(lanScadeInfoEntity.getClub_icon().getImg_url(), new Headers() {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> head = new HashMap<>();
                        head.put("Referer", lanScadeInfoEntity.getClub_icon().getSource_url());
                        return head;
                    }
                });
                Glide.with(mContext).load(cookie).into(holder.itemTripclubIconImg);
            }

        }


    }

    @Override
    public int getItemViewType(int position) {
        return Constant.viewType.typePlus;
    }

    @Override
    public int getItemCount() {
        return 1;
    }



    /**
     * 正常条目的item的ViewHolder
     */
    class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        private ImageView itemTripclubIconImg;
        private TextView itemTripclubName;
        private TextView itemTripclubFocusPeopleCount;
        private TextView itemTripclubTriplineCount;
        private TextView itemTripclubPeopleCount;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            itemTripclubIconImg = (ImageView)itemView. findViewById(R.id.item_tripclub_icon_img);
            itemTripclubName = (TextView) itemView.findViewById(R.id.item_tripclub_name);
            itemTripclubFocusPeopleCount = (TextView)itemView. findViewById(R.id.item_tripclub_focus_people_count);
            itemTripclubTriplineCount = (TextView)itemView. findViewById(R.id.item_tripclub_tripline_count);
            itemTripclubPeopleCount = (TextView)itemView. findViewById(R.id.item_tripclub_people_count);

        }
    }
}
