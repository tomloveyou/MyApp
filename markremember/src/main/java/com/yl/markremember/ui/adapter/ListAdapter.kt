package com.yl.markremember.ui.adapter

import android.content.Intent
import android.os.Bundle
import com.standards.library.adapter.BaseViewHolder
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.base.BaseMultiItemDragQuickAdapter
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.ui.activity.AddListActivity
import com.yl.markremember.ui.activity.ListLabelManagerActivity
import com.yl.markremember.ui.widget.CalendarItemView

class ListAdapter(args: List<MultiItemEntity>) : BaseMultiItemDragQuickAdapter<MultiItemEntity, BaseViewHolder>(args) {
    init {
        addItemType(0, R.layout.drawerlayout_section_item_view)
        addItemType(5, R.layout.drawerlayout_section_item_view)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        item?.run {
            if (helper?.itemViewType == 0) {
                val dd=item as MenuBean
                val ddview=helper.getView<CalendarItemView>(R.id.civ_setrion)
                ddview.setToday(dd.title)
                ddview.setLeftImg(dd.icon_resid)

                helper.itemView.setOnClickListener {
                    if (dd.isExpanded) {
                        collapse(helper.adapterPosition)
                    } else {
                        expand(helper.adapterPosition)
                    }
                }
                helper.itemView.setOnClickListener {

                }
            }else{
                val dd=item as ListInfo
                val ddview=helper?.getView<CalendarItemView>(R.id.civ_setrion)
                ddview?.setToday(dd.list_name)
                ddview?.setLeftImg(R.drawable.ic_menu)
                ddview?.setOnClickListener {
                    val bundle= Bundle()
                    bundle.putSerializable("list_data",dd)
                    val intent=Intent(mContext, AddListActivity::class.java)
                    intent.putExtras(bundle)
                    mContext. startActivity(intent)
                }
            }
        }
    }



}