package com.example.control_system.model.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserToken(
    @SerializedName("login")
    val login: String,

    @SerializedName("roleSettings")
    val roleSettings: com.example.control_system.model.data.models.RoleSettings,

    @SerializedName("sub")
    val sub: String?,

    @SerializedName("iat")
    val iat: Date?,

    @SerializedName("exp")
    val exp: Date?
)


