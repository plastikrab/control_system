package com.example.control_system.model.data.models.scenarioModel

import com.google.gson.annotations.SerializedName

data class UpdateTextModel(
    @SerializedName("report_id")val reportId: String,
    @SerializedName("text")val text: String
)
