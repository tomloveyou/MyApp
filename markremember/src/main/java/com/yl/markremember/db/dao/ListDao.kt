package com.yl.markremember.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.db.model.UserInfo

@Dao
interface ListDao {
    @Query("SELECT * FROM mark_lists WHERE mark_list_id=:id")
    fun getListById(id: Int): LiveData<ListInfo>

    @Query("SELECT * FROM mark_lists")
    fun loadAllLists(): LiveData<List<ListInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(listInfo: ListInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListList(listInfo: List<ListInfo>)

    @Delete
    suspend fun deleteList(listInfo: ListInfo);

    @Query("DELETE FROM mark_lists")
    suspend fun deleteAllList()
}