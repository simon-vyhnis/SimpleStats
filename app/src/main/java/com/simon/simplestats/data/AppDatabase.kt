package com.simon.simplestats.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Register::class, Event::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun registerDao(): RegisterDao
}