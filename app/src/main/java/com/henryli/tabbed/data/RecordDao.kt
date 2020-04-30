package com.henryli.tabbed.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface RecordDao : BaseDao<RecordEntity> {
    @Query("SELECT * FROM user_records")
    fun getAll(): LiveData<List<RecordEntity>>

    @Query("SELECT * FROM user_records WHERE title LIKE :title")
    suspend fun findByTitle(title: String): RecordEntity

}

