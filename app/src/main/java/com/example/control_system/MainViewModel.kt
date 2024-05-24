package com.example.control_system

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.control_system.data.database.MainDB
import com.example.control_system.data.database.TaskEntity
import kotlinx.coroutines.launch

class MainViewModel(val database: MainDB) : ViewModel() {

    fun insertItem() = viewModelScope.launch {
        database.dao.insertTask(taskEntity = TaskEntity(
            1,
            1,
            1,
            "aassdd",
            123,
            "asd",
            "qwe2",
            "asdf",
            "asd",
            123,
            123,
            123
        ))
    }



    companion object{
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return MainViewModel(database) as T
            }
        }
    }
}