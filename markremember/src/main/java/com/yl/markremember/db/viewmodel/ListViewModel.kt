package com.yl.markremember.db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.huayi.parkmanager.im.db.ParkDatabase
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.db.repository.LabelRepository
import com.yl.markremember.db.repository.ListRepository
import kotlinx.coroutines.launch

class ListViewModel (application: Application): AndroidViewModel(application) {

    private val repository: ListRepository
    val allLabels: LiveData<List<ListInfo>>

    init {
        val wordsDao = ParkDatabase.getDatabase(application,viewModelScope).listDao
        repository = ListRepository(wordsDao)
        allLabels = repository.allLists
    }

    fun insert(listinfo: ListInfo) = viewModelScope.launch {
        repository.insert(listinfo)
    }
    fun update(listinfo: ListInfo) = viewModelScope.launch {
        repository.update(listinfo)
    }
    fun deleteLabel(labelInfo: ListInfo)= viewModelScope.launch {
        repository.deleteList(labelInfo)
    }

}
