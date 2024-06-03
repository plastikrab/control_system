package com.example.control_system

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.auth0.android.jwt.JWT
import com.example.control_system.data.model.RoleSettings
import com.example.control_system.data.model.scenarioModel.Scenario
import com.example.control_system.data.model.UserToken
import com.example.control_system.network.getTasks
import com.example.control_system.ui.components.NavigationPannel
import com.example.control_system.ui.screens.Login
import com.example.control_system.ui.screens.MainProfileScreen
import com.example.control_system.ui.screens.MainReportsScreen
import com.example.control_system.ui.theme.Control_systemTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("tokens", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var startScreen = "loginScreen"
        var accessToken = sharedPreferences.getString("accessToken", null)

        var userToken: UserToken

        var scenrioList1 = listOf<Scenario>()

        if (accessToken == null){
            startScreen = "loginScreen"
        }
        if (accessToken != null){
            startScreen = "mainScreen"
            Log.d("MyLog", accessToken)
        }

        super.onCreate(savedInstanceState)
        setContent {


            val navController = rememberNavController()

            Control_systemTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = startScreen,
                        modifier = Modifier
                            .weight(1.0f)
                    ){
                        composable("loginScreen"){
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Login(
                                    confirmed = {
                                        editor.apply{
                                            putString("accessToken", it.body()?.accessToken)
                                            putString("refreshToken", it.body()?.refreshToken)
                                        }.apply()
                                        userToken = jwtDecoder(accessToken!!)!!
                                        getTasks(
                                            userToken,
                                            onConnectionError = {
                                                showToast()
                                            }
                                        ) {
                                            scenrioList1 = it.body()!!.data.scenarios
                                        }
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
                                    .fillMaxSize()
                            ) {
                                userToken = jwtDecoder(accessToken!!)!!
                                getTasks(
                                    userToken,
                                    onConnectionError = {
                                        showToast()
                                    }
                                ) {
                                    scenrioList1 = it.body()!!.data.scenarios
                                }
                                MainReportsScreen(scenrioList1)
                            }

                        }

                        composable("profileScreen"){
                            MainProfileScreen()
                        }
                    }
                    if (startScreen == "mainScreen"){
                        NavigationPannel(
                            tasksNavigate = {
                                navController.navigate("mainScreen")
                            },
                            profileNavigate = {
                                navController.navigate("profileScreen")
                            }
                        )
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

    private fun jwtDecoder(token: String): UserToken? {
        val jwt = JWT(token)

        val login = jwt.getClaim("login").asString() ?: return null
        val roleSettings = jwt.getClaim("roleSettings").asObject(RoleSettings::class.java)  ?: return null
        val sub = jwt.subject ?: return null
        val iat = jwt.issuedAt ?: return null
        val exp = jwt.expiresAt ?: return null

        return UserToken(
            login = login,
            roleSettings = roleSettings,
            sub = sub,
            iat = iat,
            exp = exp
        )

    }
}
