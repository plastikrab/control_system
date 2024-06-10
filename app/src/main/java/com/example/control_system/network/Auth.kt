package com.example.control_system.network

import android.util.Log
import com.example.control_system.data.model.LoginDetailsModel
import com.example.control_system.data.model.Token
import com.example.control_system.data.objects.LoginDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

fun auth(
    confirmed: (Response<Token>) -> Unit,
    wrongData : () -> Unit,
    onConnectionError : () -> Unit
){
    val container = DefaultUsersAppContainer()
    var loginData = LoginDetailsModel(
        LoginDetails.login,
        LoginDetails.password,
        LoginDetails.device
    )
    CoroutineScope(Dispatchers.IO).launch {

        try {

            val userResponse = container.usersRepository.auth(loginData)
            if (userResponse.isSuccessful){
                Log.d("MyLog", "Success")
                CoroutineScope(Dispatchers.Main).launch {
                    confirmed(userResponse)
                }
            }
            if (userResponse.errorBody() != null){
                CoroutineScope(Dispatchers.Main).launch {
                    Log.d("MyLog", "Wrong Data")
                    wrongData()
                }
            }

        } catch (e : IOException){
            Log.d("MyLog", "Fail to connect server")
            e.printStackTrace()
            CoroutineScope(Dispatchers.Main).launch(){
                onConnectionError()
            }

        }
    }
}

//TODO Доделать обработку ошибок