package com.yl.markremember.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.UserInfo

@Dao
interface LabelDao {
    @Query("SELECT * FROM mark_labels WHERE label_id=:id")
    fun getLabelById(id: Int): LiveData<LabelInfo>

    @Query("SELECT * FROM mark_labels")
    fun loadAllLabels(): LiveData<List<LabelInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLabel(labelInfo: LabelInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLabelList(labelInfo: List<LabelInfo>)

    @Query("DELETE FROM mark_labels")
    suspend fun deleteAllLabel()
}