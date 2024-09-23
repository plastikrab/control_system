package com.example.control_system.model.data.models

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