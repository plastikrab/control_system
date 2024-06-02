package com.example.control_system.data.model

data class Report(
    val title: String,
    val description: String,
    val type: String,
    val choiceReports: List<Nothing?>,
    val fileBlocks: List<Nothing?>
)