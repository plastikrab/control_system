package com.example.control_system.data.model.scenarioModel

data class Scenario(
    val _id: String,
    val scenario: ScenarioDetails,
    val status: String,
    val reportsAssigned: List<ReportAssignment>
)