package com.example.control_system

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.control_system.ui.screens.Login
import com.example.control_system.ui.theme.Control_systemTheme
import androidx.navigation.compose.rememberNavController
import com.example.control_system.data.model.Token
import com.example.control_system.ui.components.TaskCard
import com.example.control_system.ui.screens.MainReportsScreen

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("tokens", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var startScreen = "loginScreen"
        var accessToken = sharedPreferences.getString("accessToken", null)


        if (accessToken == null){
            startScreen = "loginScreen"
        }
        if (accessToken != null){
            startScreen = "mainScreen"
        }


        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Control_systemTheme {

                NavHost(
                    navController = navController,
                    startDestination = startScreen
                ){
                    composable("loginScreen"){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Login(
                                confirmed = {
                                    editor.apply{
                                        putString("accessToken", it.body()?.accessToken)
                                        putString("refreshToken", it.body()?.accessToken)
                                    }.apply()
                                    navController.navigate("mainScreen")
                                },
                                onConnectionError = {
                                    showToast()
                                })
                        }
                    }

                    composable("mainScreen"){
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            TaskCard()
                            TaskCard()
                            TaskCard()
                        }
                    }
                }
            }
        }
    }

    private fun showToast(){
        val toast = Toast.makeText(
            this,
            "Проверьте подключение к интернету",
            Toast.LENGTH_LONG
        )
        toast.show()

    }
}
