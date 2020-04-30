package com.henryli.tabbed.data.repository

import androidx.lifecycle.LiveData
import com.henryli.tabbed.data.RecordDao
import com.henryli.tabbed.data.RecordEntity

class RecordRepository(private val recordDao : RecordDao) {

    private lateinit var allRecords : LiveData<List<RecordEntity>>

    suspend fun getRecords(){
        allRecords = recordDao.getAll()
    }

}