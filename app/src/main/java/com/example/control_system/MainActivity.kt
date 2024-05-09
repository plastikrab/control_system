package com.example.control_system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import com.example.control_system.ui.screens.Login
import com.example.control_system.ui.theme.Control_systemTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            Control_systemTheme {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Login()
                    }
                }
            }
        }
    }
