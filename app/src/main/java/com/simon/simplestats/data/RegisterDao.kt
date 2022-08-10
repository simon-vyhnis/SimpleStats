package com.simon.simplestats.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDao {
    @Query("SELECT * FROM register")
    fun getRegisterList() : LiveData<List<Register>>
    @Query("SELECT COUNT (*) FROM event WHERE register_id = :registerId")
    fun getNumberOfEvents(registerId: Int) : LiveData<Int>
    @Query("SELECT * FROM event WHERE register_id = :registerId ORDER BY time ASC")
    fun getEvents(registerId: Int) : LiveData<List<Event>>
    @Insert
    suspend fun addRegister(register: Register)
    @Insert
    suspend fun addEvent(event: Event)
}