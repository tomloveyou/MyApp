package com.yl.triplibrary.ui.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.standards.library.base.BaseFuncFragment;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripHomeMainContract;
import com.yl.triplibrary.net.data.mvp.module.TripHomeMainEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.adapter.TravelingAdapter;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.ChannelEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.FilterData;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.FilterEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.FilterTwoEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.OperationEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.model.TravelingEntity;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.util.ColorUtil;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.util.DensityUtil;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.util.ModelUtil;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.util.StatusBarUtil;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.view.FilterView;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.view.HeaderBannerView;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.view.HeaderChannelView;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.view.HeaderDividerView;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.view.HeaderFilterView;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.view.HeaderOperationView;
import com.yl.triplibrary.ui.widget.StickyHeaderListView.view.SmoothListView.SmoothListView;

import java.util.ArrayList;
import java.util.List;

public class TripHomeFragmentV2 extends BaseFuncFragment implements TripHomeMainContract.TripHomeMainView,SmoothListView.ISmoothListViewListener  {

    private Activity mActivity;
    private int mScreenHeight; // 屏幕高度

    private List<String> bannerList = new ArrayList<>(); // 广告数据
    private List<ChannelEntity> channelList = new ArrayList<>(); // 频道数据
    private List<OperationEntity> operationList = new ArrayList<>(); // 运营数据
    private List<TravelingEntity> travelingList = new ArrayList<>(); // ListView数据

    private HeaderBannerView headerBannerView; // 广告视图
    private HeaderChannelView headerChannelView; // 频道视图
    private HeaderOperationView headerOperationView; // 运营视图
    private HeaderDividerView headerDividerView; // 分割线占位图
    private HeaderFilterView headerFilterView; // 分类筛选视图
    private FilterData filterData; // 筛选数据
    private TravelingAdapter mAdapter;

    private int titleViewHeight = 65; // 标题栏的高度

    private View itemHeaderBannerView; // 从ListView获取的广告子View
    private int bannerViewHeight = 180; // 广告视图的高度
    private int bannerViewTopMargin; // 广告视图距离顶部的距离

    private View itemHeaderFilterView; // 从ListView获取的筛选子View
    private int filterViewPosition = 4; // 筛选视图的位置
    private int filterViewTopMargin; // 筛选视图距离顶部的距离
    private boolean isScrollIdle = true; // ListView是否在滑动
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isSmooth = false; // 没有吸附的前提下，是否在滑动
    private int filterPosition = -1; // 点击FilterView的位置：分类(0)、排序(1)、筛选(2)
    private SmoothListView smoothListView;
    private RelativeLayout rlBar;
    private View viewTitleBg;
    private TextView tvTitle;
    private FrameLayout flActionMore;
    private View viewActionMoreBg;
    private FilterView realFilterView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trip_home_layout;
    }

    @Override
    public void init() {
        StatusBarUtil.setStatusBarTranslucent(getActivity(), false);
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mActivity = getActivity();
        mScreenHeight = DensityUtil.getWindowHeight(getActivity());

        // 筛选数据
        filterData = new FilterData();
        filterData.setCategory(ModelUtil.getCategoryData());
        filterData.setSorts(ModelUtil.getSortData());
        filterData.setFilters(ModelUtil.getFilterData());
        // 广告数据
        bannerList = ModelUtil.getBannerData();
        // 频道数据
        channelList = ModelUtil.getChannelData();
        // 运营数据
        operationList = ModelUtil.getOperationData();
        // ListView数据
        travelingList = ModelUtil.getTravelingData();
    }

    private void initView() {
        smoothListView = (SmoothListView) findView(R.id.listView);
        rlBar = (RelativeLayout) findView(R.id.rl_bar);
        viewTitleBg = (View) findView(R.id.view_title_bg);
        tvTitle = (TextView) findView(R.id.tv_title);
        flActionMore = (FrameLayout) findView(R.id.fl_action_more);
        viewActionMoreBg = (View) findView(R.id.view_action_more_bg);
        realFilterView = (FilterView) findView(R.id.real_filterView);
        // 设置广告数据
        headerBannerView = new HeaderBannerView(mActivity);
        headerBannerView.fillView(bannerList, smoothListView);

        // 设置频道数据
        headerChannelView = new HeaderChannelView(mActivity);
        headerChannelView.fillView(channelList, smoothListView);

        // 设置运营数据
        headerOperationView = new HeaderOperationView(mActivity);
        headerOperationView.fillView(operationList, smoothListView);

        // 设置分割线
        headerDividerView = new HeaderDividerView(mActivity);
        headerDividerView.fillView("", smoothListView);

        // 设置假FilterView数据
        headerFilterView = new HeaderFilterView(mActivity);
        headerFilterView.fillView(new Object(), smoothListView);

        // 设置真FilterView数据
        realFilterView.setFilterData(mActivity, filterData);
        realFilterView.setVisibility(View.GONE);

        // 设置ListView数据
        mAdapter = new TravelingAdapter(mActivity, travelingList);
        smoothListView.setAdapter(mAdapter);

        filterViewPosition = smoothListView.getHeaderViewsCount() - 1;

    }

    private void initListener() {
        // 关于
        flActionMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(mActivity, AboutActivity.class));
            }
        });

        // (假的ListView头部展示的)筛选视图点击
        headerFilterView.getFilterView().setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                isSmooth = true;
                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, titleViewHeight));
            }
        });

        // (真正的)筛选视图点击
        realFilterView.setOnFilterClickListener(new FilterView.OnFilterClickListener() {
            @Override
            public void onFilterClick(int position) {
                filterPosition = position;
                realFilterView.show(position);
                smoothListView.smoothScrollToPositionFromTop(filterViewPosition, DensityUtil.dip2px(mContext, titleViewHeight));
            }
        });

        // 分类Item点击
        realFilterView.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener() {
            @Override
            public void onItemCategoryClick(FilterTwoEntity leftEntity, FilterEntity rightEntity) {
                fillAdapter(ModelUtil.getCategoryTravelingData(leftEntity, rightEntity));
            }
        });

        // 排序Item点击
        realFilterView.setOnItemSortClickListener(new FilterView.OnItemSortClickListener() {
            @Override
            public void onItemSortClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getSortTravelingData(entity));
            }
        });

        // 筛选Item点击
        realFilterView.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener() {
            @Override
            public void onItemFilterClick(FilterEntity entity) {
                fillAdapter(ModelUtil.getFilterTravelingData(entity));
            }
        });

        smoothListView.setRefreshEnable(true);
        smoothListView.setLoadMoreEnable(true);
        smoothListView.setSmoothListViewListener(this);
        smoothListView.setOnScrollListener(new SmoothListView.OnSmoothScrollListener() {
            @Override
            public void onSmoothScrolling(View view) {
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isScrollIdle = (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScrollIdle && bannerViewTopMargin < 0) return;

                // 获取广告头部View、自身的高度、距离顶部的高度
                if (itemHeaderBannerView == null) {
                    itemHeaderBannerView = smoothListView.getChildAt(1);
                }
                if (itemHeaderBannerView != null) {
                    bannerViewTopMargin = DensityUtil.px2dip(mContext, itemHeaderBannerView.getTop());
                    bannerViewHeight = DensityUtil.px2dip(mContext, itemHeaderBannerView.getHeight());
                }

                // 获取筛选View、距离顶部的高度
                if (itemHeaderFilterView == null) {
                    itemHeaderFilterView = smoothListView.getChildAt(filterViewPosition - firstVisibleItem);
                }
                if (itemHeaderFilterView != null) {
                    filterViewTopMargin = DensityUtil.px2dip(mContext, itemHeaderFilterView.getTop());
                }

                // 处理筛选是否吸附在顶部
                if (filterViewTopMargin <= titleViewHeight || firstVisibleItem > filterViewPosition) {
                    isStickyTop = true; // 吸附在顶部
                    realFilterView.setVisibility(View.VISIBLE);
                } else {
                    isStickyTop = false; // 没有吸附在顶部
                    realFilterView.setVisibility(View.GONE);
                }

                if (isSmooth && isStickyTop) {
                    isSmooth = false;
                    realFilterView.show(filterPosition);
                }

                // 处理标题栏颜色渐变
                handleTitleBarColorEvaluate();
            }
        });
    }

    // 填充数据
    private void fillAdapter(List<TravelingEntity> list) {
        if (list == null || list.size() == 0) {
            int height = mScreenHeight - DensityUtil.dip2px(mContext, 95); // 95 = 标题栏高度 ＋ FilterView的高度
            mAdapter.setData(ModelUtil.getNoDataEntity(height));
        } else {
            mAdapter.setData(list);
        }
    }

    // 处理标题栏颜色渐变
    private void handleTitleBarColorEvaluate() {
        float fraction;
        if (bannerViewTopMargin > 0) {
            fraction = 1f - bannerViewTopMargin * 1f / 60;
            if (fraction < 0f) fraction = 0f;
            rlBar.setAlpha(fraction);
            return;
        }

        float space = Math.abs(bannerViewTopMargin) * 1f;
        fraction = space / (bannerViewHeight - titleViewHeight);
        if (fraction < 0f) fraction = 0f;
        if (fraction > 1f) fraction = 1f;
        rlBar.setAlpha(1f);

        if (fraction >= 1f || isStickyTop) {
            isStickyTop = true;
            viewTitleBg.setAlpha(0f);
            viewActionMoreBg.setAlpha(0f);
            rlBar.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            viewTitleBg.setAlpha(1f - fraction);
            viewActionMoreBg.setAlpha(1f - fraction);
            rlBar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(mContext, fraction, R.color.transparent, R.color.colorPrimary));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        headerBannerView.enqueueBannerLoopMessage();
    }

    @Override
    public void onStop() {
        super.onStop();
        headerBannerView.removeBannerLoopMessage();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (realFilterView.isShowing()) {
            realFilterView.resetAllStatus();
        } else {
           // super.onBackPressed();
        }
    }


//    @Override
//    public void onBackPressed() {
//        if (realFilterView.isShowing()) {
//            realFilterView.resetAllStatus();
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public void setListener() {

    }

    @Override
    public void getTripHomeMain(TripHomeMainEntity data) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
