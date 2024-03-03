package com.example.control_system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.control_system.ui.screens.Login
import com.example.control_system.ui.theme.Control_systemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Control_systemTheme {
                    Login()
                }
            }
        }
    }
