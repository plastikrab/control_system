package com.example.control_system.model.data.models

import com.google.gson.annotations.SerializedName

data class LoginDetailsModel (
    @SerializedName("login") val login : String,
    @SerializedName("password") val password : String,
    @SerializedName("device") val device : String
)