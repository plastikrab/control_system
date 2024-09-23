package com.example.control_system.model.network

import android.util.Log
import com.example.control_system.model.data.models.LectureRequestData
import com.example.control_system.model.data.models.UserToken
import com.example.control_system.model.data.models.scenarioModel.ServerResponse
import com.example.control_system.model.data.models.scenarioModel.UpdateChoiceReports
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class Lectures : LecturesServer {

    private val lecturesContainer = DefaultLectureAppContainer()


    override fun getTasks(
        userToken: UserToken,
        onConnectionError: () -> Unit
    ) : Response<ServerResponse> {

        val lectureRequestData = LectureRequestData(
            userToken.roleSettings.id,
            10,
            1
        )
        var tasksList : Response<ServerResponse>? = null

        CoroutineScope(Dispatchers.IO).launch {

            try {
                Log.d("MyLog", "Try to catch tasks from server")
                tasksList = lecturesContainer.lectureRepository.getTasks(lectureRequestData)
                if (tasksList.isSuccessful) {
                    Log.d("MyLog", "Success")

                }
                if (tasksList.errorBody() != null) {
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.d("MyLog", "Wrong Data")
                    }
                }
            } catch (e: IOException) {
                Log.d("MyLog", "Fail to connect server")
                e.printStackTrace()
                CoroutineScope(Dispatchers.Main).launch() {
                    onConnectionError()
                }

            } catch (e: java.lang.IllegalStateException) {
                Log.d("MyLog", "Fail to connect server")
            }
        }
        if (tasksList != null){
            return tasksList
        }

    }

    override fun updateText(
        reportId: String,
        text: String,
        onConnectionError: () -> Unit
    ){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                Log.d("MyLog", "Try to update text report")
                val tasksList = lecturesContainer.lectureRepository.updateText(reportId, text)
                if (tasksList.isSuccessful) {
                    Log.d("MyLog", "Success")
                    CoroutineScope(Dispatchers.Main).launch {

                    }
                }
                if (tasksList.errorBody() != null) {
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.d("MyLog", "Wrong Data")
                    }
                }
            }catch (e : IOException){
                Log.d("MyLog", "Fail to connect server")
                e.printStackTrace()
                CoroutineScope(Dispatchers.Main).launch(){
                    onConnectionError()
                }

            }catch (e: java.lang.IllegalStateException){
                Log.d("MyLog", "Fail to connect server")
            }
        }
    }

    override fun updateChoiceReports(
        choiceReports: UpdateChoiceReports,
        onConnectionError: () -> Unit
    ){

        CoroutineScope(Dispatchers.IO).launch {

            try {
                Log.d("MyLog", "Try to update text report")
                val tasksList = lecturesContainer.lectureRepository.updateChoiceReports(choiceReports)
                if (tasksList.isSuccessful) {
                    Log.d("MyLog", "Success")
                    CoroutineScope(Dispatchers.Main).launch {

                    }
                }
                if (tasksList.errorBody() != null) {
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.d("MyLog", "Wrong Data")
                    }
                }
            }catch (e : IOException){
                Log.d("MyLog", "Fail to connect server")
                e.printStackTrace()
                CoroutineScope(Dispatchers.Main).launch(){
                    onConnectionError()
                }

            }catch (e: java.lang.IllegalStateException){
                Log.d("MyLog", "Fail to connect server")
            }
        }
    }
}