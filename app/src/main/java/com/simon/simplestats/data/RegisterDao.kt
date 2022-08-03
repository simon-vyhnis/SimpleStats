package com.simon.simplestats.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDao {
    @Query("GET * FROM register")
    fun getRegisterList()
    @Query("GET COUNT (*) FROM event WHERE register_id = ?")
    fun getNumberOfEvents(registerId: Int)
    @Insert
    suspend fun addRegister(register: Register)
    @Insert
    suspend fun addEvent(event: Event)
}