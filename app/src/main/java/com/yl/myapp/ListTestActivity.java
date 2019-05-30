package com.yl.myapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.standards.library.base.BaseListActivity;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.standards.library.listview.listview.XRecycleListViewImpl;
import com.standards.library.listview.manager.BaseGroupListManager;

import com.yl.myapp.bean.ContentBean;
import com.standards.library.group.LoadingPage;
import com.standards.library.group.Scene;
import com.yl.myapp.manager.TesManager;

public class ListTestActivity extends BaseListActivity {
    @Override
    protected void init() {
        setTitle("控件测试");
        super.init();
    }

    @Override
    protected LoadMoreRecycleAdapter getAdapter() {
        return new Ad(this);
    }

    @Override
    protected XRecycleListViewImpl getRecycleListViewImpl() {
        return  new XRecycleListViewImpl(true, true, false);
    }

    @Override
    protected BaseGroupListManager getListManager() {
        return  new TesManager();
    }

    @Override
    protected void setListener() {



    }
    class Ad extends LoadMoreRecycleAdapter<ContentBean,Ad.ViewHolder> {

        public Ad(Context mContext) {
            super(mContext);
            removeHeaderView(0X666);//移除adapter自带的刷新的控件（Xrecyclview自带刷新和加载更多）
            removeFooterView(0X11);//移除adapter自带的加载更多的控件（Xrecyclview自带刷新和加载更多）

        }

        @Override
        public Ad.ViewHolder onCreateContentView(ViewGroup parent, int viewType) {
            return new Ad.ViewHolder(LayoutInflater.from(ListTestActivity.this).inflate(android.R.layout.simple_expandable_list_item_1, parent, false));
        }

        @Override
        public void onBindView(Ad.ViewHolder viewHolder, int realPosition) {
            viewHolder.setData(mData.get(realPosition),realPosition);
        }


        class ViewHolder  extends RecyclerView.ViewHolder{
            private TextView title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(android.R.id.text1);
            }
            public void  setData(ContentBean data, int position){
                title.setText(data.getTitle());
                title.setTextColor(getResources().getColor(R.color.black));
              
            }

        }
    }

}
