package com.example.control_system.data.model.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class ChoiceReport(
    val _id: String,
    val text: String,
    var answer: Boolean
)