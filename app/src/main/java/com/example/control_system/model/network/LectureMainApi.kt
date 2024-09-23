package com.example.control_system.model.network

import com.example.control_system.model.data.models.scenarioModel.ServerResponse
import com.example.control_system.model.data.models.scenarioModel.StartTaskModel
import com.example.control_system.model.data.models.scenarioModel.UpdateChoiceReports
import com.example.control_system.model.data.models.scenarioModel.UpdateTextModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface LectureMainApi {

    @GET("app/{userId}")
    suspend fun getTasks(
        @Path("userId") userId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<ServerResponse>

    @PATCH("app/start")
    suspend fun startTask(
        @Body startTaskModel: StartTaskModel
    ) : Response<Boolean>

    @PATCH("app/update/text")
    suspend fun updateText(
        @Body updateTextModel: UpdateTextModel
    ) : Response<Boolean>

    @PATCH("app/update/choice")
    suspend fun updateChoiceReports(
        @Body updateChoiceReports: UpdateChoiceReports
    ) : Response<Boolean>


}