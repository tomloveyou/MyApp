package com.yl.threestonecoupon.ui.home

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.widget.VerticalRecyclerView
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.yl.threestonecoupon.R
import kotlinx.android.synthetic.main.activity_coupon_main.*

class BaseListViewModel<T>(val mRecyclerView: RecyclerView):ViewModel() {
    private lateinit var mAdapter:BaseQuickAdapter<T,BaseViewHolder>
    private lateinit var mEmptyView:View;
    init {
        mEmptyView=LayoutInflater.from(mRecyclerView.context).inflate(R.layout.item_home_good_card_info,null,false)
        mAdapter=object :BaseQuickAdapter<T,BaseViewHolder>(R.layout.item_home_good_card_info){
            override fun convert(helper: BaseViewHolder?, item: T) {

            }
        }
        mAdapter.emptyView=mEmptyView;
        mAdapter.setHeaderFooterEmpty(true,true)
    }
}