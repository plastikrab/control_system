package com.example.control_system.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface LectureAppContainer{
    val lectureRepository : LecturesRepository
}


class DefaultLectureAppContainer : LectureAppContainer {
    private val LECTURE_BASE_URL = "http://10.3.16.205:8001/api/v1/"


    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(LECTURE_BASE_URL)
        .build()

    private val retrofitService : LectureMainApi by lazy {
        retrofit.create(LectureMainApi::class.java)
    }

    override val lectureRepository: LecturesRepository by lazy {
        NetworkLectureRepository(retrofitService)
    }
}