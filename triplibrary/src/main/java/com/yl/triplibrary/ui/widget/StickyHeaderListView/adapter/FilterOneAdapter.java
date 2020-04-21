package com.yl.triplibrary.ui.widget.StickyHeaderListView.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.yl.triplibrary.R;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.FilterEntity;

import java.util.List;



/**
 * Created by sunfusheng on 16/4/23.
 */
public class FilterOneAdapter extends BaseListAdapter<FilterEntity> {

    public FilterOneAdapter(Context context) {
        super(context);
    }

    public FilterOneAdapter(Context context, List<FilterEntity> list) {
        super(context, list);
    }

    public void setSelectedEntity(FilterEntity filterEntity) {
        for (FilterEntity entity : getData()) {
            entity.setSelected(filterEntity != null && entity.getKey().equals(filterEntity.getKey()));
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_filter_one, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FilterEntity entity = getItem(position);

        holder.tvTitle.setText(entity.getKey());
        if (entity.isSelected()) {
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.font_black_3));
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        ViewHolder(View view) {
            tvTitle=view.findViewById(R.id.tv_title);
            ivImage=view.findViewById(R.id.iv_image);
        }
    }
}
