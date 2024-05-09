package com.example.control_system.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(taskEntity : TaskEntity)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>


}

