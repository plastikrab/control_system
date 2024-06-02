package com.example.control_system.network

import com.example.control_system.data.model.LectureRequestData
import com.example.control_system.data.model.ServerResponse
import com.example.control_system.data.model.UserToken
import retrofit2.Response

interface LecturesRepository {

    suspend fun getTasks(lectureRequestData: LectureRequestData): Response<ServerResponse>
}

class NetworkLectureRepository(
    private val lectureService : LectureMainApi):
    LecturesRepository{
        override suspend fun getTasks(lectureRequestData: LectureRequestData): Response<ServerResponse> = lectureService.getTasks(lectureRequestData)
    }
