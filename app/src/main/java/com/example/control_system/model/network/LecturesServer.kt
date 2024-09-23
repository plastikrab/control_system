package com.example.control_system.model.network

import com.example.control_system.model.data.models.UserToken
import com.example.control_system.model.data.models.scenarioModel.ServerResponse
import com.example.control_system.model.data.models.scenarioModel.UpdateChoiceReports
import retrofit2.Response

interface LecturesServer {

    fun getTasks(
        userToken: UserToken,
        onConnectionError: () -> Unit
    ) : Response<ServerResponse>

    fun updateText(
        reportId: String,
        text: String,
        onConnectionError: () -> Unit
    )

    fun updateChoiceReports(
        choiceReports: UpdateChoiceReports,
        onConnectionError: () -> Unit
    )


}