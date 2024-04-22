package com.example.control_system.network

import com.example.control_system.data.model.User
import com.example.control_system.data.model.UserData
import com.example.control_system.data.objects.LoginDetails
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersMainApi {

    @POST("auth/login")
    suspend fun auth(@Body authRequest: LoginDetails): UserData

}
//TODO Доделать аутентификацию