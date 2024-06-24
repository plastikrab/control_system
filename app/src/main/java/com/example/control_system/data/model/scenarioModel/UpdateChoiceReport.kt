package com.example.control_system.data.model.scenarioModel

import com.google.gson.annotations.SerializedName

data class UpdateChoiceReport(
    @SerializedName("choiceReportId")val choiceReportId: String,
    @SerializedName("answer")val answer: Boolean
)
