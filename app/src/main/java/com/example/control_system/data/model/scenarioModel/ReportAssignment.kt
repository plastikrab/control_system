package com.example.control_system.data.model.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ReportAssignment(
    val _id: String,
    val report: Report,
    val choiceReportsAssigned: List<ChoiceReport>?, // This can be null, so Any is used
    val fileBlocksAssigned: List<FileBlockAssignment>,
    val createdAt: String,
    val updatedAt: String,
    var doneStatus: Boolean,
    val __v: Int
)