package com.example.control_system.data.model

data class TaskWithReport(
    val id: Int,
    val title: String,
    val description: String,
    val text: String?, // Can be null
    val createdAt: String,
    val updatedAt: String,
    val scenarioId: Int,
    val choiceReport: List<ChoiceReport> // List of ChoiceReport objects
)