package com.example.control_system.model.data.models.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class Report(
    val _id: String,
    val title: String,
    val description: String,
    val type: String,
    val choiceReports: List<com.example.control_system.model.data.models.scenarioModel.ChoiceReport>, // This can be an empty list, so Any is used
    var fileBlocks: List<com.example.control_system.model.data.models.scenarioModel.FileBlock>,
    val __v: Int
)