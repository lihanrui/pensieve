package com.henryli.tabbed.datalayer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface RecordDao : BaseDao<RecordEntity> {
    @Query("SELECT * FROM user_records")
    suspend fun getAll(): LiveData<List<RecordEntity>>

    @Query("SELECT * FROM user_records WHERE title LIKE :title")
    suspend fun findByTitle(title: String): RecordEntity

}

