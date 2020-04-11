package com.yl.markremember.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.ui.adapter.MenuExpandableAdapter
import com.yl.markremember.callback.HomeItemDragAndSwipeCallback
import com.yl.markremember.datamanager.DataManager
import com.yl.markremember.db.viewmodel.LabelViewModel
import com.yl.markremember.db.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_label_list.*

class NormalListFragment :Fragment() {
    private lateinit var labelViewModel: ListViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_label_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        labelViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val data: List<MultiItemEntity> = DataManager.homeMenuList
        val adapter = MenuExpandableAdapter(data)
        val haedview=LayoutInflater.from(activity).inflate(R.layout.label_control_layout_list_head,null)
        adapter.setHeaderFooterEmpty(true,false)
        adapter.addHeaderView(haedview)
        val dd= HomeItemDragAndSwipeCallback(adapter)
        val dddd= ItemTouchHelper(dd)
        dddd.attachToRecyclerView(recyclerView)
        adapter.enableDragItem(dddd)
        recyclerView.layoutManager=linearLayoutManager
        adapter.bindToRecyclerView(recyclerView)
        labelViewModel.allLabels.observe(this, Observer {
            adapter.setNewData(it)
        })
    }
}