package com.example.control_system.data.model

import java.util.Date

data class UserToken(
    val login: String,
    val roleSettings: RoleSettings,
    val sub: String?,
    val iat: Date?,
    val exp: Date?
)


