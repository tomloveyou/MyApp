package com.yl.markremember.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.standards.library.adapter.entity.AbstractExpandableItem
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.db.model.LabelInfo

class MenuBean(
        var icon_resid: Int,
        var id: Int,
        var title: String = "") : AbstractExpandableItem<LabelInfo>(), MultiItemEntity {
    override fun getLevel(): Int {
        return 0
    }

    override fun getItemType(): Int {
        return 0
    }
}