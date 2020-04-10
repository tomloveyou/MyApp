package com.yl.markremember.adapter

import com.standards.library.adapter.BaseViewHolder
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.db.model.LabelInfo

class MenuExpandableAdapter(args: List<MultiItemEntity>) : BaseMultiItemDragQuickAdapter<MultiItemEntity, BaseViewHolder>(args) {
    init {
        addItemType(0, R.layout.drawerlayout_section_item_view)
        addItemType(1, R.layout.item_ticke_layout)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        item?.run {
            if (helper?.itemViewType == 0) {
                val dd=item as MenuBean
                helper.setText(R.id.menu_name,dd.title)

                helper.itemView.setOnClickListener {
                    if (dd.isExpanded) {
                        collapse(helper.adapterPosition)
                    } else {
                        expand(helper.adapterPosition)
                    }
                }
            }else{
                val dd=item as LabelInfo
                helper?.setText(R.id.menu_name,dd.label_name)
                helper?.setText(R.id.menu_right_text,"${dd.label_use_count}")
            }
        }
    }



}