package com.yl.markremember.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.standards.library.adapter.entity.AbstractExpandableItem
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo

class MenuBean(
        var icon_resid: Int,
        var pid: Int?,
        var id: Int?,
        var view_type:Int,
        var title: String = "") : AbstractExpandableItem<LabelInfo>(), MultiItemEntity {
    override fun getLevel(): Int {
        return 0
    }
    fun clearSubItems(){
        mSubItems.clear()
    }

    override fun getItemType(): Int {
        return view_type
//        return if (id == 51352) {//今天
//            0
//        } else {
//            1
//        }//文字类型
    }
}