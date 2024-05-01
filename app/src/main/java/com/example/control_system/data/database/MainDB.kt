package com.example.control_system.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        TaskEntity::class,
        ChoiceReportEntity::class,
        TaskEntity::class
    ],
    version = 1
)
abstract class MainDB : RoomDatabase(){


    companion object{
        fun createDataBase(context : Context): MainDB{
            return Room.databaseBuilder(
                context,
                MainDB::class.java,
                "MainDb.db"
            ).build()
        }
    }
}