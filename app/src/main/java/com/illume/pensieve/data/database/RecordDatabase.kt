package com.illume.pensieve.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.illume.pensieve.data.RecordEntity
import com.illume.pensieve.data.dao.RecordDao

@Database(entities = [RecordEntity::class], version = 1, exportSchema = false)
abstract class RecordDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile
        private var instance: RecordDatabase? = null
        private val LOCK = Any()


        fun getDatabase(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            RecordDatabase::class.java, "user_records.db"
        ).build()
    }
}