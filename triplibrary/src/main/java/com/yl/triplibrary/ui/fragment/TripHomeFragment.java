package com.yl.triplibrary.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.lcodecore.tkrefreshlayout.utils.DensityUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.standards.library.base.BaseFuncFragment;
import com.standards.library.util.Util;
import com.standards.library.util.Utils;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripHomeMainContract;
import com.yl.triplibrary.net.data.mvp.module.TripHomeMainEntity;
import com.yl.triplibrary.net.data.mvp.presenter.TripHomeMainPresenter;
import com.yl.triplibrary.ui.activity.adapter.LanScadeDetailBodyTitleAdapter;
import com.yl.triplibrary.ui.activity.adapter.home.TripHomeBannerAdapter;
import com.yl.triplibrary.ui.activity.adapter.home.TripHomeGvAdapter;
import com.yl.triplibrary.ui.activity.adapter.home.TripHomeRecomentAdapter;
import com.yl.triplibrary.ui.activity.adapter.home.TripHomeSortSeachAdapter;
import com.yl.triplibrary.ui.activity.adapter.home.TripSearchAdapter;

public class TripHomeFragment extends BaseFuncFragment implements TripHomeMainContract.TripHomeMainView {
    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private DelegateAdapter adapters;
    private TripHomeMainPresenter tripHomeMainPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_refresh_recycleview;
    }

    @Override
    public void init() {
        tripHomeMainPresenter=new TripHomeMainPresenter(this);
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);

        myRecyclerView.setLayoutManager(layoutManager);

        adapters = new DelegateAdapter(layoutManager, false);
        FixLayoutHelper searchHelp = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT,0,50);
        TripSearchAdapter searchAdapter=new TripSearchAdapter(mContext,searchHelp);
        adapters.addAdapter(searchAdapter);
        myRecyclerView.setAdapter(adapters);
        tripHomeMainPresenter.getHomeTripData();
       // mPresenter.getLanScadeDeailData(true);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void getTripHomeMain(TripHomeMainEntity data) {
        /*banner*/
        if (data.getBanners_list()!=null&&data.getBanners_list().size()>0){
            SingleLayoutHelper bannerHelp = new SingleLayoutHelper();
            TripHomeBannerAdapter bannerAdapter=new TripHomeBannerAdapter(mContext,bannerHelp,data.getBanners_list());
            adapters.addAdapter(bannerAdapter);
        }
        /*设置九宫格数据*/
        if (data.getGrid_buttons_list()!=null&&data.getGrid_buttons_list().size()>0){
            GridLayoutHelper gvHelp = new GridLayoutHelper(4);
            int dp= DensityUtil.dp2px(mContext, 20);
            gvHelp.setMargin(dp, dp, dp, dp);
            gvHelp.setVGap(dp);
            gvHelp.setHGap(dp);
            TripHomeGvAdapter gvAdapter=new TripHomeGvAdapter(mContext,gvHelp,data.getGrid_buttons_list());
            adapters.addAdapter(gvAdapter);
        }
        /*设置分类搜索数据*/
        if (data.getConoditionEntity()!=null){
            StickyLayoutHelper sortSearchHelp = new StickyLayoutHelper();
            TripHomeSortSeachAdapter sortSearchAdapter=new TripHomeSortSeachAdapter(mContext,sortSearchHelp,data.getConoditionEntity());
            adapters.addAdapter(sortSearchAdapter);
        }
        /*设置热门推荐title*/
        SingleLayoutHelper hotTitleHelp = new SingleLayoutHelper();
        LanScadeDetailBodyTitleAdapter hotTitleAdapter=new LanScadeDetailBodyTitleAdapter(mContext,hotTitleHelp,"热门推荐自驾游");
        adapters.addAdapter(hotTitleAdapter);
        /*设置推荐数据*/
        if (data.getRecomment_list()!=null&&data.getRecomment_list().size()>0){
            LinearLayoutHelper recommntrHelp = new LinearLayoutHelper();
            TripHomeRecomentAdapter recommntAdapter=new TripHomeRecomentAdapter(mContext,recommntrHelp,data.getRecomment_list());
            adapters.addAdapter(recommntAdapter);
        }
    }
}
