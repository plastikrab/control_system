package com.example.control_system.data.model

data class User(
    val id: Int,
    val login: String,
    val role: String,
    val email: String?,
    val surname: String,
    val name: String,
    val patronymic: String,
    val roleSettings: List<RoleSetting>,
    val device: List<Any>
)
