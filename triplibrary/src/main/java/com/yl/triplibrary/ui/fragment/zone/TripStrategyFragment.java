package com.yl.triplibrary.ui.fragment.zone;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.contract.RankTripContract;
import com.yl.triplibrary.net.data.mvp.presenter.RankTripPresenter;
import com.yl.triplibrary.ui.activity.adapter.RankTripAdapter2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 旅游攻略
*@user yl
*@date 11:25
**/
public class TripStrategyFragment extends BaseFuncFragment<RankTripPresenter> implements RankTripContract.RankTripView {


    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private RankTripAdapter2 tripAdapter2;
    List<RankTripZone> da = new ArrayList<>();

    @Override
    protected RankTripPresenter getPresenter() {
        return new RankTripPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lanscade;
    }

    @Override
    public void init() {
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        tripAdapter2 = new RankTripAdapter2(da);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(manager);
        tripAdapter2.bindToRecyclerView(myRecyclerView);
        mPresenter.getRankTripData(true);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getRankTripData(false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getRankTripData(false);
            }
        });
        tripAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<RankTripZone> da = new ArrayList<>();
                        String url_head = tripAdapter2.getItem(position).getUrl();
                        try {
                            Document doc = Jsoup.connect(url_head).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                            String origial_html=doc.toString();
                            String head=doc.select(".web980").select(".header").toString();
                            String head_people_info=doc.select(".web980").select(".jingdian-head").select(".mddxqqg").toString();
                            String foot=doc.select(".footer").toString();
                            String html=origial_html.replace(head, "");


                            Logger.d(html);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


    @Override
    public void getRankTrip(List<RankTripZone> data) {

        if (mPresenter.getCurrent_page() == 1) {
            tripAdapter2.setNewData(data);
            smartRefreshLayout.finishRefresh();
        } else {
            tripAdapter2.addData(data);
            smartRefreshLayout.finishLoadmore();
        }

        //  tripAdapter2.notifyDataSetChanged();
    }
}
