package com.example.control_system.model.data.models.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ScenarioData(
    val totalPages: Int,
    var scenarios: MutableList<Scenario>
)