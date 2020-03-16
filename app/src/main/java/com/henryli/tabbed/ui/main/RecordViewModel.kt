package com.henryli.tabbed.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryli.tabbed.datalayer.RecordEntity

class RecordViewModel : ViewModel() {
    private val records: MutableLiveData<List<RecordEntity>> by lazy {
        MutableLiveData<List<RecordEntity>>().also {
            loadRecords()
        }
    }

    fun getUsers(): LiveData<List<RecordEntity>> {
        return records
    }

    private fun loadRecords() {
        // Do an asynchronous operation to fetch users.
    }
}