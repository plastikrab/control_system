package com.example.control_system.data.model.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class Report(
    val _id: String,
    val title: String,
    val description: String,
    val type: String,
    val choiceReports: List<ChoiceReport>, // This can be an empty list, so Any is used
    var fileBlocks: List<FileBlock>,
    val __v: Int
)