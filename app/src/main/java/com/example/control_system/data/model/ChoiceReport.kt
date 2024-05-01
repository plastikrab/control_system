package com.example.control_system.data.model

data class ChoiceReport(
    val id: Int,
    val text: String,
    val answer: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val reportId: Int
)