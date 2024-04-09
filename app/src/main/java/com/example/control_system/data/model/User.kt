package com.example.control_system.data.model

data class User(
    //val id: Int,
    val login: String,
    val role: String,
    val email: String?,
    val name: String,
    val surname: String,
    val patronymic: String,
    //val roleSettings: List<RoleSetting>,
    //val device: List<Any>
)
