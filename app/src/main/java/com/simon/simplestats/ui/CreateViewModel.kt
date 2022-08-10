package com.simon.simplestats.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.simon.simplestats.data.AppDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.simon.simplestats.data.Event
import com.simon.simplestats.data.Register
import kotlinx.coroutines.launch

class CreateViewModel(context: Application) : AndroidViewModel(context) {
    private val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "db"
    )
        .fallbackToDestructiveMigration()
        .build()
    private val registerDao = db.registerDao()

    fun addEvent(event: Event) = viewModelScope.launch{
        registerDao.addEvent(event)
    }

    fun getRegisterList() : LiveData<List<Register>>{
        return registerDao.getRegisterList()
    }

    fun addRegister(register: Register) = viewModelScope.launch{
        registerDao.addRegister(register)
    }

}