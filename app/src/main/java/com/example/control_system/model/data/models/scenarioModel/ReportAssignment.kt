package com.example.control_system.model.data.models.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ReportAssignment(
    val _id: String,
    val report: com.example.control_system.model.data.models.scenarioModel.Report,
    val choiceReportsAssigned: List<com.example.control_system.model.data.models.scenarioModel.ChoiceReport>?, // This can be null, so Any is used
    val fileBlocksAssigned: List<com.example.control_system.model.data.models.scenarioModel.FileBlockAssignment>,
    val createdAt: String,
    val updatedAt: String,
    var doneStatus: Boolean,
    val __v: Int
)