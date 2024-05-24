package com.example.control_system.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey


@Entity(tableName = "choice_reports",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ])
data class ChoiceReportEntity(
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
    val text: String,
    val answer: Boolean,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
    val taskId: Int,
    var isEdited: Boolean = false // Добавьте поле isEdited
)