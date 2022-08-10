package com.simon.simplestats.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Register::class, Event::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun registerDao(): RegisterDao
}