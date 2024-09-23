package com.example.control_system.model.data.models.scenarioModel

import kotlinx.serialization.Serializable

@Serializable
data class FileBlock(
    val _id: String,
    var text: String,
    val __v: Int?
)