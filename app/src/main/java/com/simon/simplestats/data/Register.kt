package com.simon.simplestats.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Register(
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "haveValues") var values : Boolean
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}