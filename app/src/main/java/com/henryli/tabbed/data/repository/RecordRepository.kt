package com.henryli.tabbed.data.repository

import androidx.lifecycle.LiveData
import com.henryli.tabbed.data.dao.RecordDao
import com.henryli.tabbed.data.RecordEntity

class RecordRepository(private val recordDao : RecordDao) {

    val allRecords : LiveData<List<RecordEntity>> = recordDao.getAll()

    suspend fun insert(record : RecordEntity){
        recordDao.insert(record)
    }

}