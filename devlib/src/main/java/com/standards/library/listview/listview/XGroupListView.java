package com.standards.library.listview.listview;

import android.content.Context;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.standards.library.listview.adapter.IGroupAdapter;

import rx.Observable;

/**
 * @author yl
 * @version V1.0
 * @email 84743672@qq.com
 * @date 2015-10-16 下午4:14:56
 */
public interface XGroupListView<T> {

    void initView(Context context, IGroupAdapter<T> adapter);

    View getRootView();

    View getRecycleView();

    boolean isAutoReFresh();

    void AutoRefresh();

    /**
     * 结束下拉刷新
     */
    void onRefreshComplete();

    /**
     * 结束更多
     */
    void onLoadMoreComplete();

    /**
     * 设置监听事件
     *
     * @param listener
     */
    void setLoadingListener(XRecyclerView.LoadingListener listener);

    void setNoMore(boolean isnomore);
    void setEmptyView(View emptyView);

}
