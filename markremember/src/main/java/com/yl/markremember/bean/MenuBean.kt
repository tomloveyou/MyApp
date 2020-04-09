package com.yl.markremember.bean

import com.standards.library.adapter.entity.AbstractExpandableItem
import com.standards.library.adapter.entity.MultiItemEntity

class MenuBean(var id:Int,var title:String) : AbstractExpandableItem<MenuSubBean>(), MultiItemEntity {
    override fun getLevel(): Int {
        return 0
    }

    override fun getItemType(): Int {
        return 0
    }
}