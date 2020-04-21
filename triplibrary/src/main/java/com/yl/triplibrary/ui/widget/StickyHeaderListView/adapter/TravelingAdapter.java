package com.yl.triplibrary.ui.widget.StickyHeaderListView.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.standards.library.widget.EaseImageView;
import com.yl.triplibrary.R;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.TravelingEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.util.ToastUtil;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by sunfusheng on 16/4/20.
 */
public class TravelingAdapter extends BaseListAdapter<TravelingEntity> {

    private boolean isNoData;
    private int mHeight;
    public static final int ONE_SCREEN_COUNT = 8; // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_REQUEST_COUNT = 10; // 一次请求的个数

    public TravelingAdapter(Context context) {
        super(context);
    }

    public TravelingAdapter(Context context, List<TravelingEntity> list) {
        super(context, list);
    }

    // 设置数据
    public void setData(List<TravelingEntity> list) {
        clearAll();
        addALL(list);

        isNoData = false;
        if (list.size() == 1 && list.get(0).isNoData()) {
            // 暂无数据布局
            isNoData = list.get(0).isNoData();
            mHeight = list.get(0).getHeight();
        } else {
            // 添加空数据
            if (list.size() < ONE_SCREEN_COUNT) {
                addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
            }
        }
        notifyDataSetChanged();
    }

    // 创建不满一屏的空数据
    public List<TravelingEntity> createEmptyList(int size) {
        List<TravelingEntity> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i=0; i<size; i++) {
            emptyList.add(new TravelingEntity());
        }
        return emptyList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 暂无数据
        if (isNoData) {
            convertView = mInflater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
            RelativeLayout rootView = convertView.findViewById(R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return convertView;
        }

        // 正常数据
        final ViewHolder holder;
        if (convertView != null && convertView instanceof LinearLayout) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_travel, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        TravelingEntity entity = getItem(position);

        holder.llRootView.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(entity.getType())) {
            holder.llRootView.setVisibility(View.INVISIBLE);
            return convertView;
        }

        final String title = entity.getFrom() + entity.getTitle() + entity.getType();
        holder.tvTitle.setText(title);
        holder.tvRank.setText("排名：" + entity.getRank());
        Glide.with(mContext).load(entity.getImage_url()).into( holder.givImage);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(mContext, title);
            }
        });

        return convertView;
    }

    static class ViewHolder {

        LinearLayout llRootView;

        EaseImageView givImage;

        TextView tvTitle;

        TextView tvRank;

        ViewHolder(View view) {
            tvRank=view.findViewById(R.id.tv_rank);
            llRootView=view.findViewById(R.id.ll_root_view);
            tvTitle=view.findViewById(R.id.tv_title);
            givImage=view.findViewById(R.id.giv_image);
        }
    }
}
