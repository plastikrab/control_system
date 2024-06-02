package com.example.control_system.data.model

data class Scenario(
    val createdByUserId: Int,
    val title: String,
    val description: String,
    val reports: List<Report>
)