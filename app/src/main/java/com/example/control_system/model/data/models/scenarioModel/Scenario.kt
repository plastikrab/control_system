package com.example.control_system.model.data.models.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class Scenario(
    val _id: String,
    val scenario: ScenarioDetails,
    var status: String,
    val reportsAssigned: MutableList<com.example.control_system.model.data.models.scenarioModel.ReportAssignment>
)