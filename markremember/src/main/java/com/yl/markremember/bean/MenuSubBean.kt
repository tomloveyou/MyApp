package com.yl.markremember.bean

import com.standards.library.adapter.entity.AbstractExpandableItem
import com.standards.library.adapter.entity.MultiItemEntity

class MenuSubBean(var parent_menu_id:Int,var title: String) : MultiItemEntity {

    override fun getItemType(): Int {
        return 1
    }
}