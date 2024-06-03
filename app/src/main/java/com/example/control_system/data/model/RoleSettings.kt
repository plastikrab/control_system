package com.example.control_system.data.model

import com.google.gson.annotations.SerializedName

data class RoleSettings(

    @SerializedName("id")
    val id: Int,

    @SerializedName("user")
    val user: Boolean,

    @SerializedName("manager")
    val manager: Boolean,

    @SerializedName("analyst")
    val analyst: Boolean,

    @SerializedName("administrator")
    val administrator: Boolean
)