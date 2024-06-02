package com.example.control_system.data.model

data class ServerResponse(
    val status: Int,
    val message: String,
    val data: List<Scenario>
)