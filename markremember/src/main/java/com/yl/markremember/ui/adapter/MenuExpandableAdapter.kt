package com.yl.markremember.ui.adapter

import android.content.Intent
import com.standards.library.adapter.BaseViewHolder
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.base.BaseMultiItemDragQuickAdapter
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.ui.activity.ListLabelManagerActivity
import com.yl.markremember.ui.widget.CalendarItemView

class MenuExpandableAdapter(args: List<MultiItemEntity>) : BaseMultiItemDragQuickAdapter<MultiItemEntity, BaseViewHolder>(args) {
    init {
        addItemType(0, R.layout.drawerlayout_section_item_view)
        addItemType(1, R.layout.drawerlayout_section_groupitem_view)//
        addItemType(2, R.layout.item_ticke_layout)
        addItemType(4,R.layout.view_divider_line)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        item?.run {
            if (helper?.itemViewType == 1) {
                val dd=item as MenuBean
                val ddview=helper.getView<CalendarItemView>(R.id.civ_setrion)
                ddview.setToday(dd.title)
                ddview.changeStatus(!dd.isExpanded)
                helper.itemView.setOnClickListener {
                    if (dd.isExpanded) {
                        ddview.changeStatus(false)
                        collapse(helper.adapterPosition)
                    } else {
                        ddview.changeStatus(true)
                        expand(helper.adapterPosition)
                    }
                }

            }else if (helper?.itemViewType == 0){
                val dd=item as MenuBean
                val ddview=helper.getView<CalendarItemView>(R.id.civ_setrion)
                ddview.setToday(dd.title)
            }else if(helper?.itemViewType == 2){
                val dd=item as LabelInfo
//                val ddview=helper.getView<CalendarItemView>(R.id.civ_setrion)
//                ddview.setToday(dd.label_name)
                //helper?.setText(R.id.menu_right_text,"${dd.label_use_count}")
            }else{

            }
        }
    }



}