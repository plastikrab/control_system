package com.example.control_system.network

import android.util.Log
import com.example.control_system.data.model.User
import com.example.control_system.data.model.UserData
import com.example.control_system.data.objects.LoginDetails

interface UserRepository {
    suspend fun auth(authRequest: LoginDetails): UserData
}

class NetworkUserRepository(
    private val userService : UsersMainApi
) : UserRepository {
    override suspend fun auth(
        authRequest: LoginDetails
    ): UserData = userService.auth(authRequest)
}