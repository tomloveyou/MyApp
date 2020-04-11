package com.yl.markremember.db.repository

import androidx.lifecycle.LiveData
import com.yl.markremember.db.dao.LabelDao
import com.yl.markremember.db.model.LabelInfo

class LabelRepository(var labelDao: LabelDao) {
    val allLabels: LiveData<List<LabelInfo>> = labelDao.loadAllLabels()

    suspend fun insert(todo: LabelInfo){
        labelDao.insertLabel(todo)
    }
    fun getLabelById(id: Int){
        labelDao.getLabelById(id)
    }
    suspend fun deleteLabel(labelInfo: LabelInfo){
        labelDao.deleteLabel(labelInfo)
    }
}