package com.example.control_system.model.data.models.scenarioModel

import com.google.gson.annotations.SerializedName

data class StartTaskModel(
    @SerializedName("startTime") val startTime : String,
    @SerializedName("longitude_start") val longitudeStart : String,
    @SerializedName("latitude_start") val latitudeStart : String,
    @SerializedName("scenarioId") val scenarioId : String
)
