package com.henryli.tabbed.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_records")
data class RecordEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "time")
    var time: Long = 0,

    @ColumnInfo(name = "latitude")
    var lat: Double = 0.0,

    @ColumnInfo(name = "longitude")
    var lon: Double = 0.0,

    @ColumnInfo(name = "mood")
    var mood: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "note")
    var note: String = "",

    @ColumnInfo(name = "action_category")
    var category: Int = 0 // todo implement later

)