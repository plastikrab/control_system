package com.example.control_system.data.model

data class Task(
    val id: Int,
    val createdByUserId: Int,
    val assignedToUserId: Int,
    val status: String,
    val startTime: String, // Can be converted to a Date object if needed
    val geolocationStart: String,
    val geolocationEnd: String?, // Allow null for geolocationEnd
    val title: String,
    val description: String,
    val endTime: String?, // Allow null for endTime
    val createdAt: String,
    val updatedAt: String
)