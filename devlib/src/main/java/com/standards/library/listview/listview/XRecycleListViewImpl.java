package com.standards.library.listview.listview;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.standards.library.R;
import com.standards.library.listview.adapter.IGroupAdapter;

/**
 * <recycleView组件>
 *
 * @data: 2015/11/21 20:16
 * @version: V1.0
 */
public class XRecycleListViewImpl<T> implements XGroupListView<T> {

    private static final int DEFAULT_SPAN_COUNT = 1;

    private View mRootView;
    private XRecyclerView mListView;
    private IGroupAdapter<T> mAdapter;
    private boolean isVertical = true;
    private boolean isAutoRefresh;

    //还不清楚GridLayoutManager与LinearLayoutManager的性能差别。假如性能有区别，之后需要根据SpanCount使用不同的LayoutManager
    private LinearLayoutManager mLayoutManager;



    private boolean mRefreshEnable, mLoadMoreEnable;

    public XRecycleListViewImpl(boolean refreshEnable, boolean loadMoreEnable) {
        this(refreshEnable, loadMoreEnable, true);
    }

    public XRecycleListViewImpl(boolean refreshEnable, boolean loadMoreEnable, boolean isAutoRefresh) {
        this.mRefreshEnable = refreshEnable;
        this.mLoadMoreEnable = loadMoreEnable;
        this.isAutoRefresh = isAutoRefresh;

    }


    @Override
    public void initView(Context context, IGroupAdapter<T> mAdapter) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.group_xrecycle_listview, null);
        mListView = (XRecyclerView) mRootView.findViewById(R.id.my_xrecycler_view);
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        }

        mListView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
      //  mListView.getDefaultFootView().setBackgroundColor(context.getResources().getColor(R.color.blue));//设置刷新和加载背景颜色
//       mListView.getDefaultRefreshHeaderView().setBackgroundColor(context.getResources().getColor(R.color.blue));//设置刷新和加载背景颜色
        mListView.setLayoutManager(mLayoutManager);

//        mListView.setItemAnimator(new MyItemAnimator());
        mListView.setRefreshProgressStyle(ProgressStyle.Pacman);
        mListView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mListView.setPullRefreshEnabled(mRefreshEnable);
        mListView.setLoadingMoreEnabled(mLoadMoreEnable);
        try {
            mListView.setAdapter((RecyclerView.Adapter) mAdapter);

            this.mAdapter = mAdapter;
        } catch (Exception e) {
            throw new RuntimeException("适配器adpter必须继承RecyclerView.Adapter抽象类和实现IGroupAdapter接口");
        }

    }

    public void selectItem(int position) {
        if (mListView != null) {
            mListView.smoothScrollToPosition(position);
        }
    }



    public RecyclerView getRecyclerView() {
        return mListView;
    }



    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public View getRecycleView() {
        return mListView;
    }

    @Override
    public boolean isAutoReFresh() {
        return isAutoRefresh;
    }

    @Override
    public void AutoRefresh() {
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.refresh();
            }
        }, 150);
    }

    @Override
    public void onRefreshComplete() {
        mListView.refreshComplete();

    }

    @Override
    public void onLoadMoreComplete() {
        mListView.loadMoreComplete();
    }

    @Override
    public void setLoadingListener(XRecyclerView.LoadingListener listener) {
        mListView.setLoadingListener(listener);
    }

    @Override
    public void setNoMore(boolean isnomore) {
        mListView.setNoMore(isnomore);
    }

    @Override
    public void setEmptyView(View emptyView) {
        mListView.setEmptyView(emptyView);
    }


    public void setLayoutManager(GridLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    public boolean getRefreshEnable() {
        return mRefreshEnable;
    }


}
