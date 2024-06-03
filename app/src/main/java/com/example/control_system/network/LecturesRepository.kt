package com.example.control_system.network

import com.example.control_system.data.model.LectureRequestData
import com.example.control_system.data.model.scenarioModel.ServerResponse
import retrofit2.Response

interface LecturesRepository {

    suspend fun getTasks(lectureRequestData: LectureRequestData): Response<ServerResponse>
}

class NetworkLectureRepository(
    private val lectureService : LectureMainApi):
    LecturesRepository{
        override suspend fun getTasks(
            lectureRequestData: LectureRequestData
        ): Response<ServerResponse> = lectureService.getTasks(
            lectureRequestData.userId,
            lectureRequestData.limit,
            lectureRequestData.page
        )
    }
