package com.example.control_system.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val createdByUserId: Int,
    val assignedToUserId: Int?,
    val status: String,
    val startTime: Long?,
    val geolocationStart: String?,
    val geolocationEnd: String?,
    val title: String,
    val description: String,
    val endTime: Long?,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
)