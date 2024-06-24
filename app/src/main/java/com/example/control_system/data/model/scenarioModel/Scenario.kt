package com.example.control_system.data.model.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class Scenario(
    val _id: String,
    val scenario: ScenarioDetails,
    var status: String,
    val reportsAssigned: MutableList<ReportAssignment>
)