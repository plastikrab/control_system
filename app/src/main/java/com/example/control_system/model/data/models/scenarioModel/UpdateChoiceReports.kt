package com.example.control_system.model.data.models.scenarioModel

import com.google.gson.annotations.SerializedName

data class UpdateChoiceReports(
    @SerializedName("reportId") var reportId: String,
    @SerializedName("choiceReports")val choiceReports: List<UpdateChoiceReport>
)
