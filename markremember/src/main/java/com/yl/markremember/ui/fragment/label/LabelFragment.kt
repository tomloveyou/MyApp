package com.yl.markremember.ui.fragment.label

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnSelectListener
import com.lxj.xpopup.util.XPopupUtils
import com.yl.markremember.R
import com.yl.markremember.callback.HomeItemDragAndSwipeCallback
import com.yl.markremember.db.viewmodel.LabelViewModel
import com.yl.markremember.ui.activity.AddLabelActivity
import com.yl.markremember.ui.adapter.LabelAdapter
import kotlinx.android.synthetic.main.fragment_label_list.*

/**
 * 标签管理页面
 */
class LabelFragment : Fragment() {
    private lateinit var labelViewModel: LabelViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_label_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        labelViewModel = ViewModelProvider(this).get(LabelViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapter = LabelAdapter(arrayListOf())
        val haedview = LayoutInflater.from(activity).inflate(R.layout.label_control_layout_list_head, null)
        adapter.setHeaderFooterEmpty(true, true)
        adapter.addHeaderView(haedview)
        val footview = LayoutInflater.from(activity).inflate(R.layout.label_control_layout_list_foot, null)
        adapter.addFooterView(footview)
        val dd = HomeItemDragAndSwipeCallback(adapter)
        val dddd = ItemTouchHelper(dd)
        dddd.attachToRecyclerView(recyclerView)
        adapter.enableDragItem(dddd)
        recyclerView.layoutManager = linearLayoutManager
        adapter.bindToRecyclerView(recyclerView)
        labelViewModel.allLabels.observe(this, Observer {
            adapter.setNewData(it)
        })
        adapter.footerLayout.setOnClickListener {
            startActivity(Intent(activity, AddLabelActivity::class.java))
        }
        haedview.setOnClickListener {
            XPopup.Builder(activity).asCenterList("标签", arrayOf("自动","显示","隐藏"), IntArray(0),0,object : OnSelectListener{
                override fun onSelect(position: Int, text: String?) {

                }
            }).show()
        }

    }
}