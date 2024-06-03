package com.example.control_system.network

import com.example.control_system.data.model.scenarioModel.ServerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LectureMainApi {

    @GET("app/{userId}")
    suspend fun getTasks(
        @Path("userId") userId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<ServerResponse>
}