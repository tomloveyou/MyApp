package com.yl.markremember.ui.activity

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standards.library.adapter.entity.MultiItemEntity
import com.standards.library.adapter.listener.OnItemDragListener
import com.standards.library.base.BaseFuncActivity
import com.standards.library.base.BasePresenter
import com.yl.markremember.R
import com.yl.markremember.adapter.MenuExpandableAdapter
import com.yl.markremember.callback.HomeItemDragAndSwipeCallback
import com.yl.markremember.datamanager.DataManager
import kotlinx.android.synthetic.main.activity_calendar_main.*


class CalendarMainActivity : BaseFuncActivity<BasePresenter<*>>() {
    override fun setListener() {

    }

    override fun init() {
        val nav: View = nav_view.getHeaderView(0)
        val recyclerView = nav.findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val data: List<MultiItemEntity> =DataManager.homeMenuList
        val adapter = MenuExpandableAdapter(data)
        adapter.setHeaderAndEmpty(true)
        val dd= HomeItemDragAndSwipeCallback(adapter)
        val dddd= ItemTouchHelper(dd)
        dddd.attachToRecyclerView(recyclerView)
        adapter.enableDragItem(dddd)
        adapter.setOnItemDragListener(object :OnItemDragListener{
            override fun onItemDragMoving(source: RecyclerView.ViewHolder?, from: Int, target: RecyclerView.ViewHolder?, to: Int) {

            }

            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

            }
        })
        val headview: View = LayoutInflater.from(this).inflate(R.layout.drawerlayout_head_view, null, false)
        adapter.setHeaderView(headview)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_main
    }
}