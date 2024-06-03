package com.example.control_system.data.model.scenarioModel

data class ServerResponse(
    val status: Int,
    val message: String,
    val data: ScenarioData
)