package com.yl.userlibrary;

import com.standards.library.base.BaseListActivity;
import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.standards.library.listview.listview.XRecycleListViewImpl;
import com.standards.library.listview.manager.BaseGroupListManager;
import com.yl.userlibrary.adapter.FuctionUsedAdapter;
import com.yl.userlibrary.manager.FuctionUsedManager;

public class FuctionUsedActivity extends BaseListActivity {
    @Override
    protected void init() {
        setTitle("使用记录");
        super.init();
    }
    @Override
    protected LoadMoreRecycleAdapter getAdapter() {
        return new FuctionUsedAdapter(this);
    }

    @Override
    protected XRecycleListViewImpl getRecycleListViewImpl() {
        return new XRecycleListViewImpl(true, true, false);
    }

    @Override
    protected BaseGroupListManager getListManager() {
        return new FuctionUsedManager();
    }

}
