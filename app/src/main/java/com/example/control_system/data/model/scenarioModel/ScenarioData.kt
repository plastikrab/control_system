package com.example.control_system.data.model.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ScenarioData(
    val totalPages: Int,
    var scenarios: MutableList<Scenario>
)