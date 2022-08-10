package com.simon.simplestats.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
    @ColumnInfo(name = "time") var time : Long,
    @ColumnInfo(name = "value") var value : Double?,
    @ColumnInfo(name= "register_id") var registerId : Int
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}