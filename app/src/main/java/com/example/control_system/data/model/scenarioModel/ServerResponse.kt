package com.example.control_system.data.model.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ServerResponse(
    val status: Int,
    val message: String,
    val data: ScenarioData
)