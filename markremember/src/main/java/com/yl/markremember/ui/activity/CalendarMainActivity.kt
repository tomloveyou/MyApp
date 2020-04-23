package com.yl.markremember.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standards.library.adapter.entity.MultiItemEntity
import com.standards.library.base.BaseFuncActivity
import com.standards.library.base.BasePresenter
import com.yl.markremember.R
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.ui.adapter.MenuExpandableAdapter
import com.yl.markremember.callback.HomeItemDragAndSwipeCallback
import com.yl.markremember.common.NetConstant
import com.yl.markremember.datamanager.DataManager
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.db.viewmodel.LabelViewModel
import com.yl.markremember.db.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_calendar_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.drawerlayout_foot_view.view.*


class CalendarMainActivity : BaseFuncActivity<BasePresenter<*>>() {
    private lateinit var listViewModel: ListViewModel
    private lateinit var labelViewModel: LabelViewModel
    private lateinit var data: ArrayList<MultiItemEntity>
    private lateinit var headdata: List<MultiItemEntity>
    private lateinit var label_menu: MenuBean
    private var adapter: MenuExpandableAdapter? = null;
    override fun setListener() {
        labelViewModel.allLabels.observe(this, Observer {
            label_menu.subItems = it
            adapter?.notifyDataSetChanged()

        })
        listViewModel.allLabels.observe(this, Observer {
            val menuBeanList = java.util.ArrayList<MultiItemEntity>()
            menuBeanList.addAll(headdata)
            menuBeanList.addAll(it)
            adapter?.setNewData(menuBeanList)

        })
    }

    override fun init() {
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        labelViewModel = ViewModelProvider(this).get(LabelViewModel::class.java)
        val nav: View = nav_view.getHeaderView(0)
        val recyclerView = nav.findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        headdata = DataManager.homeMenuList
        data = ArrayList()
        label_menu = headdata.find {
            val menu = it as MenuBean
            menu.id == 51353
        } as MenuBean
        data.addAll(headdata)
        adapter = MenuExpandableAdapter(data)
        val headview: View = LayoutInflater.from(this).inflate(R.layout.drawerlayout_head_view, null, false)
        val footview: View = LayoutInflater.from(this).inflate(R.layout.drawerlayout_foot_view, null, false)

        adapter?.run {
            setHeaderAndEmpty(true)
            val itemTouchHelper = ItemTouchHelper(HomeItemDragAndSwipeCallback(this))
            itemTouchHelper.attachToRecyclerView(recyclerView)
            enableDragItem(itemTouchHelper)
            setHeaderView(headview)
            setFooterView(footview)
            recyclerView.adapter = this
            setOnItemClickListener { adapter, view, position ->
                val viewtype = getItemViewType(position)
                if (viewtype == 2) {//标签
                    //点击跳转page2
                   // Navigation.findNavController(view).navigate(R.id.ragment_task_list_to_nav_gallery)
                } else if (viewtype == 5) {//清单
                    //点击跳转page2
                   // Navigation.findNavController(view).navigate(R.id.ragment_task_list_to_nav_gallery)
                }
            }
        }

        footview.list_label_manager_view.setOnClickListener {
            startActivity(Intent(this, ListLabelManagerActivity::class.java))
        }
        footview.add_label_view.setOnClickListener {
            startActivity(Intent(this, AddListActivity::class.java))
        }

    }

    override fun onSupportNavigateUp() =
            Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_main
    }
}