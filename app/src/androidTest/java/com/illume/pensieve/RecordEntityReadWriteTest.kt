package com.illume.pensieve

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.illume.pensieve.data.database.RecordDatabase
import com.illume.pensieve.data.dao.RecordDao
import com.illume.pensieve.data.RecordEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RecordEntityReadWriteTest {
    private lateinit var recordDao: RecordDao
    private lateinit var db: RecordDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, RecordDatabase::class.java).build()
        recordDao = db.recordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val recordEntity: RecordEntity = RecordEntity()
//        recordEntity.mood = Mood.HAPPY
        recordEntity.title = "A test title"
        recordEntity.note = "A test note"

        recordDao.insert(recordEntity)
        val recordItem = recordDao.findByTitle(recordEntity.title)
        assert(recordItem != null)
        assert(recordItem.title.equals("A test title"))

//        val list : List<RecordEntity>? = recordItem.value
//        if(list != null){
//            assert(list.size != 0)
//            assertThat(list.get(0), equalTo(recordEntity))
//        }
    }
}