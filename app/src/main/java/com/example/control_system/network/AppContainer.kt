package com.example.control_system.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer{
    val userRepository : UserRepository
}


class DefaultAppContainer : AppContainer {
    private val USERS_BASE_URL = "http://10.3.15.207:8000/api/"


    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(USERS_BASE_URL)
        .build()

    private val retrofitService : UsersMainApi by lazy {
        retrofit.create(UsersMainApi::class.java)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(retrofitService)
    }
}