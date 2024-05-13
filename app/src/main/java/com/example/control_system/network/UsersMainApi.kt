package com.example.control_system.network

import com.example.control_system.data.model.LoginDetailsModel
import com.example.control_system.data.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersMainApi {

    @POST("auth/login")
    suspend fun auth(
        @Body authRequest: LoginDetailsModel
    ): Response<Token>

}
//TODO Доделать аутентификацию