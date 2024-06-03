package com.example.control_system.data.model.scenarioModel

data class Report(
    val _id: String,
    val title: String,
    val description: String,
    val type: String,
    val choiceReports: List<Any>, // This can be an empty list, so Any is used
    val fileBlocks: List<FileBlock>,
    val __v: Int
)