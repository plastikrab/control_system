package com.example.control_system.network

import android.util.Log
import com.auth0.jwt.JWT
import com.example.control_system.data.model.LoginDetailsModel
import com.example.control_system.data.model.Token
import com.example.control_system.data.objects.LoginDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

fun auth(
    confirmed: (Response<Token>) -> Unit,
    wrongData : () -> Unit
){
    var container = DefaultAppContainer()
    var LoginData = LoginDetailsModel(
        LoginDetails.login, LoginDetails.password,
        LoginDetails.device
    )
    CoroutineScope(Dispatchers.IO).launch {

        try {

            val userResponse = container.userRepository.auth(LoginData)
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
        }
    }
}

//TODO Доделать обработку ошибок