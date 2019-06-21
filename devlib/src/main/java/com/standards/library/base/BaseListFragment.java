package com.standards.library.base;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.standards.library.R;
import com.standards.library.group.LoadingPage;
import com.standards.library.group.Scene;
import com.standards.library.listview.XListGroupPresenter;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.standards.library.listview.listview.XRecycleListViewImpl;
import com.standards.library.listview.manager.BaseGroupListManager;
import com.standards.library.widget.RecycleViewDivider;

public abstract class BaseListFragment extends BaseFuncFragment {
    protected XListGroupPresenter presenter;
    private XRecycleListViewImpl recycleListView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_style;
    }

    @Override
    public void init() {
        recycleListView = getRecycleListViewImpl();
        RelativeLayout rlContent = findView(R.id.activity_list_containner);
        LoadingPage loadingPage = new LoadingPage(mContext, Scene.DEFAULT);
        presenter = XListGroupPresenter.create(mContext, recycleListView,getListManager(), getAdapter(), loadingPage);
        recycleListView.getRecyclerView().addItemDecoration(new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.main_black_color_999999)));
        rlContent.addView(presenter.getRootView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
    /**
     * recycleview适配器
     * @return
     */
    abstract protected LoadMoreRecycleAdapter getAdapter();

    /**
     * 控制列表控件的设置属性，例如是否可以刷新，是否可以加载更多，是否自动更新
     * @return
     */
    abstract protected XRecycleListViewImpl getRecycleListViewImpl();

    /**
     * 列表数据的请求模块
     * @return
     */
    abstract protected BaseGroupListManager getListManager();

    @Override
    public void setListener() {

    }


}
