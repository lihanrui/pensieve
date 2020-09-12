package com.illume.pensieve.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.illume.pensieve.data.RecordEntity

@Dao
interface RecordDao : BaseDao<RecordEntity> {
    @Query("SELECT * FROM user_records")
    fun getAll(): LiveData<List<RecordEntity>>

    @Query("SELECT * FROM user_records WHERE title LIKE :title")
    fun findByTitle(title: String): RecordEntity

}

