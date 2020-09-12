package com.henryli.tabbed.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.henryli.tabbed.data.RecordEntity
import com.henryli.tabbed.data.database.RecordDatabase
import com.henryli.tabbed.data.repository.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : RecordRepository
    val allRecords : LiveData<List<RecordEntity>>

    init {
        val wordsDao = RecordDatabase.getDatabase(application).recordDao()
        repository = RecordRepository(wordsDao)
        allRecords = repository.allRecords
    }

    fun insert(record : RecordEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(record)
    }
}