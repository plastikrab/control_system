package com.example.control_system.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface UsersAppContainer{
    val usersRepository : UsersRepository
}


class DefaultUsersAppContainer : UsersAppContainer {
    private val USERS_BASE_URL = "http://10.3.16.205:8000/api/v1/"


    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(USERS_BASE_URL)
        .build()

    private val retrofitService : UsersMainApi by lazy {
        retrofit.create(UsersMainApi::class.java)
    }

    override val usersRepository: UsersRepository by lazy {
        NetworkUsersRepository(retrofitService)
    }
}