package com.yl.userlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.yl.userlibrary.R;
import com.yl.userlibrary.mvp.module.UsedLogBean;

public class FuctionUsedAdapter extends LoadMoreRecycleAdapter<UsedLogBean,FuctionUsedAdapter.ViewHolder> {

    public FuctionUsedAdapter(Context mContext) {
        super(mContext);
        removeHeaderView(0X666);//移除adapter自带的刷新的控件（Xrecyclview自带刷新和加载更多）
        removeFooterView(0X11);//移除adapter自带的加载更多的控件（Xrecyclview自带刷新和加载更多）

    }

    @Override
    public FuctionUsedAdapter.ViewHolder onCreateContentView(ViewGroup parent, int viewType) {
        return new FuctionUsedAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(android.R.layout.simple_expandable_list_item_1, parent, false));
    }

    @Override
    public void onBindView(FuctionUsedAdapter.ViewHolder viewHolder, int realPosition) {
        viewHolder.setData(mData.get(realPosition),realPosition);
    }


    class ViewHolder  extends RecyclerView.ViewHolder{
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(android.R.id.text1);
        }
        public void  setData(UsedLogBean data, int position){
            title.setText(data.getUsed_content());
            title.setTextColor(mContext.getResources().getColor(R.color.black));

        }

    }
}
