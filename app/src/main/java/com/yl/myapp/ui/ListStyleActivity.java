package com.yl.myapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.listview.ListGroupPresenter;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.standards.library.listview.listview.RecycleListViewImpl;
import com.yl.myapp.R;

import com.yl.myapp.bean.ContentBean;
import com.yl.myapp.bean.ControlBean;
import com.standards.library.group.LoadingPage;
import com.standards.library.group.Scene;
import com.yl.myapp.manager.TesManager;
import com.standards.library.widget.RecycleViewDivider;


public class ListStyleActivity extends BaseTitleBarActivity {
    private ListGroupPresenter presenter;
    private RecycleListViewImpl recycleListView;
    private ControlBean controlBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_style;
    }

    @Override
    public void getExtra() {
        super.getExtra();
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            controlBean= (ControlBean) bundle.getSerializable("data");
        }

    }

    @Override
    protected void init() {
       // setTitle(controlBean.getRESOURCE_NAME());
        recycleListView = new RecycleListViewImpl(true, true, false);
        RelativeLayout rlContent = findView(R.id.activity_list_containner);
        LoadingPage loadingPage = new LoadingPage(this, Scene.DEFAULT);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        presenter = ListGroupPresenter.create(ListStyleActivity.this, recycleListView, new TesManager(), new Ad(this), loadingPage);
        recycleListView.getRecyclerView().addItemDecoration(new RecycleViewDivider(this,
                LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.main_black_color_999999)));
        rlContent.addView(presenter.getRootView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void setListener() {

    }
    class Ad extends LoadMoreRecycleAdapter<ContentBean,Ad.ViewHolder> {

        public Ad(Context mContext) {
            super(mContext);
//            removeHeaderView(0X666);
//            removeFooterView(0X11);

        }

        @Override
        public ViewHolder onCreateContentView(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(ListStyleActivity.this).inflate(android.R.layout.simple_expandable_list_item_1, parent, false));
        }

        @Override
        public void onBindView(ViewHolder viewHolder, int realPosition) {
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
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        List<ControlBean> dd = LitePal.where("PID = ? and RESOURCE_TYPE= ?", data.getRESOURCE_ID(),"MENU").find(ControlBean.class);
//                        if (dd == null || dd.size() == 0) {
//                            startActivity(new Intent(ListStyleActivity.this, WebActivity.class));
//
//                        }else {
//                            Intent intent = new Intent(ListStyleActivity.this, ListStyleActivity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("data", data);
//                            intent.putExtras(bundle);
//                            startActivity(intent);
//                        }
//                    }
//                });
            }

        }
    }


}
