package com.example.control_system.network

import com.example.control_system.data.model.LectureRequestData
import com.example.control_system.data.model.scenarioModel.ServerResponse
import com.example.control_system.data.model.scenarioModel.StartTaskModel
import com.example.control_system.data.model.scenarioModel.UpdateChoiceReports
import com.example.control_system.data.model.scenarioModel.UpdateTextModel
import retrofit2.Response

interface LecturesRepository {

    suspend fun getTasks(
        lectureRequestData: LectureRequestData
    ): Response<ServerResponse>

    suspend fun startTask(
        startTime: String,
        longitudeStart: String,
        latitudeString: String,
        scenarioId: String,
    ) : Response<Boolean>

    suspend fun updateText(
        reportId: String,
        text: String
    ) : Response<Boolean>

    suspend fun updateChoiceReports(
        updateChoiceReports: UpdateChoiceReports
    ) : Response<Boolean>
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

        override suspend fun startTask(
            startTime: String,
            longitudeStart: String,
            latitudeString: String,
            scenarioId: String,
        ) : Response<Boolean> = lectureService.startTask(
            StartTaskModel(
                startTime,
                longitudeStart,
                latitudeString,
                scenarioId
            )
        )

        override suspend fun updateText(
            reportId : String,
            text : String
        ) : Response<Boolean> = lectureService.updateText(
            UpdateTextModel(
                reportId,
                text
            )
        )

        override suspend fun updateChoiceReports(
            updateChoiceReports: UpdateChoiceReports
        ): Response<Boolean> = lectureService.updateChoiceReports(
            updateChoiceReports
        )
}
