package com.example.control_system.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ])
data class TaskWithChoiceReports(
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
    val createdAt: Long,
    val updatedAt: Long,
    val taskId: Int,
    @Embedded val choiceReports: List<ChoiceReportEntity>
)