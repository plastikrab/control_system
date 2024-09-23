package com.example.control_system.model.data.models.scenarioModel

import com.google.gson.annotations.SerializedName

data class UpdateChoiceReport(
    @SerializedName("choiceReportId")val choiceReportId: String,
    @SerializedName("answer")val answer: Boolean
)
