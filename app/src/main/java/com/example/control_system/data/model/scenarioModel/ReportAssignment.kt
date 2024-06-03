package com.example.control_system.data.model.scenarioModel

data class ReportAssignment(
    val _id: String,
    val report: Report,
    val choiceReportsAssigned: Any?, // This can be null, so Any is used
    val fileBlocksAssigned: List<FileBlockAssignment>,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)