package com.example.control_system.data.model.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ScenarioDetails(
    val _id: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val __v: Int
)