package com.yl.triplibrary.ui.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.triplibrary.R;
import com.yl.triplibrary.ui.activity.adapter.home.TripHomeSortSeachAdapter;
import com.yl.triplibrary.ui.activity.adapter.home.TripSearchAdapter;

public class TimeSearchAvtivity extends BaseTitleBarActivity {
    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_recycleview;
    }

    @Override
    protected void init() {
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        myRecyclerView.setLayoutManager(layoutManager);

        DelegateAdapter   adapters = new DelegateAdapter(layoutManager, false);
        FixLayoutHelper sortSearchHelp = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT,0,50);
        TripHomeSortSeachAdapter sortSearchAdapter=new TripHomeSortSeachAdapter(this,sortSearchHelp,null);
        adapters.addAdapter(sortSearchAdapter);

        myRecyclerView.setAdapter(adapters);
    }

    @Override
    protected void setListener() {

    }
}
