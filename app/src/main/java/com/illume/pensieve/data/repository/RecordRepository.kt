package com.illume.pensieve.data.repository

import androidx.lifecycle.LiveData
import com.illume.pensieve.data.dao.RecordDao
import com.illume.pensieve.data.RecordEntity

class RecordRepository(private val recordDao : RecordDao) {

    val allRecords : LiveData<List<RecordEntity>> = recordDao.getAll()

    suspend fun insert(record : RecordEntity){
        recordDao.insert(record)
    }

}