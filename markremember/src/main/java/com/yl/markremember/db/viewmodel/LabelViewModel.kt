package com.yl.markremember.db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.huayi.parkmanager.im.db.ParkDatabase
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.db.repository.LabelRepository
import kotlinx.coroutines.launch

class LabelViewModel (application: Application): AndroidViewModel(application) {

    private val repository: LabelRepository
    val allLabels: LiveData<List<LabelInfo>>

    init {
        val wordsDao = ParkDatabase.getDatabase(application,viewModelScope).labelDao
        repository = LabelRepository(wordsDao)
        allLabels = repository.allLabels
    }

    fun insert(labelinfo: LabelInfo) = viewModelScope.launch {
        repository.insert(labelinfo)
    }

    fun update(labelinfo: LabelInfo) = viewModelScope.launch {
        repository.upDateLabel(labelinfo)
    }

    fun deleteLabel(labelInfo: LabelInfo)= viewModelScope.launch {
        repository.deleteLabel(labelInfo)
    }


}