package com.yl.markremember.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.standards.library.adapter.entity.MultiItemEntity
import java.io.Serializable

/**
 * 清单
 */
@Entity(tableName = "mark_lists")
class ListInfo(@ColumnInfo(name = "mark_list_id")
                @PrimaryKey(autoGenerate = true)
                var list_id: Int?,
               @ColumnInfo(name = "mark_list_pid")
               var list_pid: Int,
               @ColumnInfo(name = "mark_list_tint_color")
                var list_tint_color: String?,
               @ColumnInfo(name = "mark_list_name")
                var list_name: String,
               @ColumnInfo(name = "mark_list_create_time")
                var list_create_time: String,
               @ColumnInfo(name = "mark_list_update_time")
                var list_update_time: String?,
               @ColumnInfo(name = "mark_list_use_count")
                var list_use_count: Int?)
    : MultiItemEntity ,Serializable{

    override fun getItemType(): Int {
        return 5
    }
}