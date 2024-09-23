package com.example.control_system.model.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersMainApi {

    @POST("auth/login")
    suspend fun auth(
        @Body authRequest: com.example.control_system.model.data.models.LoginDetailsModel
    ): Response<com.example.control_system.model.data.models.Token>

}