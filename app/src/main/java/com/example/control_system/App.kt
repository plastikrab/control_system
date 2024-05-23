package com.example.control_system

import android.app.Application
import com.example.control_system.data.database.MainDB


class App : Application() {
    val database by lazy {
        MainDB.createDataBase(this)
    }
}