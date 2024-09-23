package com.example.control_system.model.network

import retrofit2.Response

interface UsersRepository {
    suspend fun auth(authRequest: com.example.control_system.model.data.models.LoginDetailsModel): Response<com.example.control_system.model.data.models.Token>
}

class NetworkUsersRepository(
    private val userService : UsersMainApi
) : UsersRepository {
    override suspend fun auth(
        authRequest: com.example.control_system.model.data.models.LoginDetailsModel
    ): Response<com.example.control_system.model.data.models.Token> = userService.auth(authRequest)
}