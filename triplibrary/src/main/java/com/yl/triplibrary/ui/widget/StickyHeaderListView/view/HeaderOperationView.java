package com.yl.triplibrary.ui.widget.StickyHeaderListView.view;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yl.triplibrary.R;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.adapter.HeaderOperationAdapter;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.OperationEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.util.ToastUtil;

import java.util.List;


/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderOperationView extends AbsHeaderView<List<OperationEntity>> {


    FixedGridView gvOperation;

    public HeaderOperationView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<OperationEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_operation_layout, listView, false);
        gvOperation=view.findViewById(R.id.gv_operation);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<OperationEntity> list) {
        if (list == null || list.size() < 2 || list.size() > 6) return;
        if (list.size()%2 != 0) return;

        final HeaderOperationAdapter adapter = new HeaderOperationAdapter(mActivity, list);
        gvOperation.setAdapter(adapter);

        gvOperation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show(mActivity, adapter.getItem(position).getTitle());
            }
        });
    }

}
