package com.yl.threestonecoupon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.yl.threestonecoupon.R
import com.yl.threestonecoupon.bean.rep.RepGoodListBean
import com.yl.threestonecoupon.bean.req.ReqGoodListBean
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mAdapter:BaseQuickAdapter<RepGoodListBean,BaseViewHolder>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter=object :BaseQuickAdapter<RepGoodListBean,BaseViewHolder>(R.layout.item_home_good_card_info){
            override fun convert(helper: BaseViewHolder, item: RepGoodListBean?) {
                helper.apply {
                    setText(R.id.tv_goods_title,item?.title)
                    setText(R.id.tv_current_price,item?.actualPrice.toString())
                    val option=RequestOptions().placeholder(R.drawable.ic_loading).error(R.drawable.ic_load_empty)
                    Glide.with(mContext).load(item?.marketingMainPic).apply(option).into(getView(R.id.iv_good_img))
                }
            }

        }
        recyclerView.apply {
            layoutManager=StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter=mAdapter
        }
        refreshLayout.setOnRefreshListener {
            homeViewModel.queryGoodListByCondition()
        }
        homeViewModel.goods.observe(viewLifecycleOwner, Observer {
            refreshLayout.finishRefresh()
           mAdapter.setNewData(it)
        })
    }
}