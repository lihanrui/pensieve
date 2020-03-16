package com.henryli.tabbed.datalayer.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.henryli.tabbed.datalayer.RecordDao

class RecordRepository(private val recordDao : RecordDao) {

    private lateinit var allRecords = recordDao.getAll();

    fun getRecords(){
        LifecycleOwner
        allRecords = recordDao.getAll()
    }

}