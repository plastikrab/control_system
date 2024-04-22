package com.example.control_system.network

import android.util.Log
import com.example.control_system.data.objects.LoginDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

fun auth(){
    var container = DefaultAppContainer()
    CoroutineScope(Dispatchers.IO).launch {

        try {
            val user = container.userRepository.auth(LoginDetails)
            Log.d("MyLog", user.toString())
        } catch (e : IOException){
            Log.d("MyLog", "Error")
            e.printStackTrace()
        }
    }
}

//TODO Доделать обработку ошибок