package com.example.control_system.network

import com.example.control_system.data.model.LoginDetailsModel
import com.example.control_system.data.model.Token
import retrofit2.Response

interface UserRepository {
    suspend fun auth(authRequest: LoginDetailsModel): Response<Token>
}

class NetworkUserRepository(
    private val userService : UsersMainApi
) : UserRepository {
    override suspend fun auth(
        authRequest: LoginDetailsModel
    ): Response<Token> = userService.auth(authRequest)
}