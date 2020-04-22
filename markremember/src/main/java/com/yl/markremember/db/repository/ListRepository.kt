package com.yl.markremember.db.repository

import androidx.lifecycle.LiveData
import com.yl.markremember.db.dao.LabelDao
import com.yl.markremember.db.dao.ListDao
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo

class ListRepository(var listDao: ListDao) {
    val allLists: LiveData<List<ListInfo>> = listDao.loadAllLists()

    suspend fun insert(todo: ListInfo){
        listDao.insertList(todo)
    }
    suspend fun update(todo: ListInfo){
        listDao.updateList(todo)
    }
    suspend fun deleteList(todo: ListInfo){
        listDao.deleteList(todo)
    }
}