package com.yl.triplibrary.ui.widget.StickyHeaderListView.view;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yl.triplibrary.R;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.adapter.HeaderChannelAdapter;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.ChannelEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.util.ToastUtil;

import java.util.List;


/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderChannelView extends AbsHeaderView<List<ChannelEntity>> {

    FixedGridView gvChannel;

    public HeaderChannelView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<ChannelEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_channel_layout, listView, false);

        gvChannel=view.findViewById(R.id.gv_channel);
        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(final List<ChannelEntity> list) {
        if (list == null || list.size() < 2) return;
        int size = list.size();
        if (size <= 4) {
            gvChannel.setNumColumns(size);
        } else if (size == 6) {
            gvChannel.setNumColumns(3);
        } else if (size == 8) {
            gvChannel.setNumColumns(4);
        } else {
            return;
        }

        final HeaderChannelAdapter adapter = new HeaderChannelAdapter(mActivity, list);
        gvChannel.setAdapter(adapter);

        gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show(mActivity, adapter.getItem(position).getTitle());
            }
        });
    }

}
