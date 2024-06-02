package com.example.control_system.data.model

data class RoleSettings(
    val id: Int,
    val user: Boolean,
    val manager: Boolean,
    val analyst: Boolean,
    val administrator: Boolean
)