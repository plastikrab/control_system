package com.example.control_system.model.data.models.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ServerResponse(
    val status: Int,
    val message: String,
    val data: ScenarioData
)