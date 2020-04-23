package com.yl.markremember.ui.fragment.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.yl.markremember.R
import com.yl.markremember.db.model.TaskInfo
import kotlinx.android.synthetic.main.fragment_task_list.*

class LabelTaskListFragment :Fragment(){
    private var adapter: BaseQuickAdapter<TaskInfo, BaseViewHolder>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        adapter=object : BaseQuickAdapter<TaskInfo, BaseViewHolder>(R.layout.item_ticke_normal_layout){
            override fun convert(helper: BaseViewHolder?, item: TaskInfo?) {

            }

        }
        val emptyview=LayoutInflater.from(activity).inflate(R.layout.fragment_task_label_empty,null)
        adapter?.run {
            setHeaderAndEmpty(true)
            setEmptyView(emptyview)
            bindToRecyclerView(recyclerView)
        }

    }
}