package com.yl.markremember.adapter

import com.standards.library.adapter.BaseViewHolder
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.bean.MenuBean

class MenuExpandableAdapter(args: ArrayList<MultiItemEntity>) : BaseMultiItemDragQuickAdapter<MultiItemEntity, BaseViewHolder>(args) {
    init {
        addItemType(0, R.layout.drawerlayout_section_item_view)
        addItemType(1, R.layout.item_ticke_layout)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        item?.run {
            if (helper?.itemViewType == 0) {
                val dd=item as MenuBean
                helper.itemView.setOnClickListener {
                    if (dd.isExpanded) {
                        collapse(helper.adapterPosition)
                    } else {
                        expand(helper.adapterPosition)
                    }
                }
            }
        }
    }



}