package com.example.control_system.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
    val createdByUserId: Int,
    val assignedToUserId: Int?,
    val status: String,
    val startTime: Long?,
    val geolocationStart: String?,
    val geolocationEnd: String?,
    val title: String,
    val description: String,
    val endTime: Long?,
    val createdAt: Long,
    val updatedAt: Long
)