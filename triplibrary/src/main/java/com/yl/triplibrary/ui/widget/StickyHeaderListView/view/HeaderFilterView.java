package com.yl.triplibrary.ui.widget.StickyHeaderListView.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.yl.triplibrary.R;


/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderFilterView extends AbsHeaderView<Object> {


    private FilterView fakeFilterView;

    public HeaderFilterView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(Object obj, ListView listView) {
        View view = mInflate.inflate(R.layout.header_filter_layout, listView, false);
        fakeFilterView = (FilterView)view. findViewById(R.id.fake_filterView);
        listView.addHeaderView(view);
    }

    public FilterView getFilterView() {
        return fakeFilterView;
    }


}
