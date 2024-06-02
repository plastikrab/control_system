package com.example.control_system.network

import com.example.control_system.data.model.LectureRequestData
import com.example.control_system.data.model.LoginDetailsModel
import com.example.control_system.data.model.ServerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface LectureMainApi {

    @GET
    suspend fun getTasks(
        @Body lectureRequestData: LectureRequestData
    ): Response<ServerResponse>
}