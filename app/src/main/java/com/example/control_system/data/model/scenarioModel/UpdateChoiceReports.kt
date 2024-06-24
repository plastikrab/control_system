package com.example.control_system.data.model.scenarioModel

import com.google.gson.annotations.SerializedName

data class UpdateChoiceReports(
    @SerializedName("reportId") var reportId: String,
    @SerializedName("choiceReports")val choiceReports: List<UpdateChoiceReport>
)
