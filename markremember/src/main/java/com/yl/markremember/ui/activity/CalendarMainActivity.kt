package com.yl.markremember.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standards.library.adapter.entity.MultiItemEntity
import com.standards.library.base.BaseFuncActivity
import com.standards.library.base.BasePresenter
import com.yl.markremember.R
import com.yl.markremember.ui.adapter.MenuExpandableAdapter
import com.yl.markremember.callback.HomeItemDragAndSwipeCallback
import com.yl.markremember.datamanager.DataManager
import kotlinx.android.synthetic.main.activity_calendar_main.*
import kotlinx.android.synthetic.main.drawerlayout_foot_view.view.*


class CalendarMainActivity : BaseFuncActivity<BasePresenter<*>>() {
    override fun setListener() {

    }

    override fun init() {
        val nav: View = nav_view.getHeaderView(0)
        val recyclerView = nav.findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val data: List<MultiItemEntity> = DataManager.homeMenuList
        val adapter = MenuExpandableAdapter(data)
        adapter.setHeaderAndEmpty(true)
        val dd = HomeItemDragAndSwipeCallback(adapter)
        val dddd = ItemTouchHelper(dd)
        dddd.attachToRecyclerView(recyclerView)
        adapter.enableDragItem(dddd)
        val headview: View = LayoutInflater.from(this).inflate(R.layout.drawerlayout_head_view, null, false)
        val footview: View = LayoutInflater.from(this).inflate(R.layout.drawerlayout_foot_view, null, false)
        adapter.setHeaderView(headview)
        adapter.setFooterView(footview)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        footview.ll_list_label_manager.setOnClickListener {
            startActivity(Intent(this, ListLabelManagerActivity::class.java))
        }
        footview.ll_add_label.setOnClickListener {
            startActivity(Intent(this, AddListActivity::class.java))
        }

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_main
    }
}