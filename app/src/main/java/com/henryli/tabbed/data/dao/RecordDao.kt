package com.henryli.tabbed.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.henryli.tabbed.data.RecordEntity

@Dao
interface RecordDao : BaseDao<RecordEntity> {
    @Query("SELECT * FROM user_records")
    fun getAll(): LiveData<List<RecordEntity>>

    @Query("SELECT * FROM user_records WHERE title LIKE :title")
    fun findByTitle(title: String): RecordEntity

}

