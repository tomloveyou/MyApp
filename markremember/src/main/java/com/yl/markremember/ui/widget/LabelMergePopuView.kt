package com.yl.markremember.ui.widget

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.core.CenterPopupView
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.yl.markremember.R
import com.yl.markremember.db.model.LabelInfo
import kotlinx.android.synthetic.main.wiget_label_merge.view.*

class LabelMergePopuView(context: Context) :CenterPopupView(context) {
    override fun onCreate() {
        super.onCreate()
        val  linearLayoutManager=LinearLayoutManager(context)
        linearLayoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=linearLayoutManager
        val labeladapter=object :BaseQuickAdapter<LabelInfo,BaseViewHolder>(R.layout._xpopup_center_impl_confirm){
            override fun convert(helper: BaseViewHolder?, item: LabelInfo?) {

            }

        }
    }
    override fun getImplLayoutId(): Int {
        return R.layout.wiget_label_merge
    }
}