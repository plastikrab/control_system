package com.example.control_system.data.model.scenarioModel

import com.google.gson.annotations.SerializedName

data class UpdateTextModel(
    @SerializedName("report_id")val reportId: String,
    @SerializedName("text")val text: String
)
