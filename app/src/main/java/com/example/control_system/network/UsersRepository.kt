package com.example.control_system.network

import com.example.control_system.data.model.LoginDetailsModel
import com.example.control_system.data.model.Token
import retrofit2.Response

interface UsersRepository {
    suspend fun auth(authRequest: LoginDetailsModel): Response<Token>
}

class NetworkUsersRepository(
    private val userService : UsersMainApi
) : UsersRepository {
    override suspend fun auth(
        authRequest: LoginDetailsModel
    ): Response<Token> = userService.auth(authRequest)
}