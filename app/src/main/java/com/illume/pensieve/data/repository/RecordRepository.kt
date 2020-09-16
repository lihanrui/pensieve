package com.illume.pensieve.data.repository

import androidx.lifecycle.LiveData
import com.illume.pensieve.data.RecordEntity
import com.illume.pensieve.data.dao.RecordDao

class RecordRepository(private val recordDao: RecordDao) {

    val allRecords: LiveData<List<RecordEntity>> = recordDao.getAll()

    suspend fun insert(record: RecordEntity) {
        recordDao.insert(record)
    }

}