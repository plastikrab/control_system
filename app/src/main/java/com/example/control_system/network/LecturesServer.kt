package com.example.control_system.network

import android.util.Log
import com.example.control_system.data.model.LectureRequestData
import com.example.control_system.data.model.UserToken
import com.example.control_system.data.model.scenarioModel.ServerResponse
import com.example.control_system.data.model.scenarioModel.UpdateChoiceReports
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

object LecturesServer {

    private val lecturesContainer = DefaultLectureAppContainer()


    fun getTasks(
        userToken: UserToken,
        onConnectionError: () -> Unit,
        confirmed: (Response<ServerResponse>) -> Unit,
    ){

        val lectureRequestData = LectureRequestData(
            userToken.roleSettings.id,
            10,
            1
        )

        CoroutineScope(Dispatchers.IO).launch {

            try {
                Log.d("MyLog", "Try to catch tasks from server")
                val tasksList = lecturesContainer.lectureRepository.getTasks(lectureRequestData)
                if (tasksList.isSuccessful) {
                    Log.d("MyLog", "Success")
                    CoroutineScope(Dispatchers.Main).launch {
                        confirmed(tasksList)
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

    fun updateText(
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

    fun updateChoiceReports(
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