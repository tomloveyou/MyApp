package com.yl.markremember.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.standards.library.adapter.entity.MultiItemEntity
import java.io.Serializable

@Entity(tableName = "mark_labels")
class LabelInfo(@ColumnInfo(name = "label_id")
                @PrimaryKey(autoGenerate = true)
                var label_id: Int?,
                @ColumnInfo(name = "label_pid")
                var label_pid: Int,
                @ColumnInfo(name = "label_pname")
                var label_pname: String?,
                @ColumnInfo(name = "label_tint_color")
                var label_tint_color: String?,
                @ColumnInfo(name = "label_name")
                var label_name: String,
                @ColumnInfo(name = "label_create_time")
                var label_create_time: String,
                @ColumnInfo(name = "label_update_time")
                var label_update_time: String?,
                @ColumnInfo(name = "label_use_count")
                var label_use_count: Int)
    : MultiItemEntity,Serializable{

    override fun getItemType(): Int {
        return 2
    }
}