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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.auth0.android.jwt.JWT
import com.example.control_system.data.model.RoleSettings
import com.example.control_system.data.model.UserToken
import com.example.control_system.data.model.scenarioModel.SavedScenarios
import com.example.control_system.data.model.scenarioModel.ScenarioData
import com.example.control_system.network.LecturesServer
import com.example.control_system.network.TaskStatus
import com.example.control_system.ui.components.NavigationPannel
import com.example.control_system.ui.screens.Login
import com.example.control_system.ui.screens.MainProfileScreen
import com.example.control_system.ui.screens.MainReportsScreen
import com.example.control_system.ui.theme.Control_systemTheme
import io.paperdb.Paper

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        val savedTokens = getSharedPreferences("tokens", Context.MODE_PRIVATE)
        val tokensEditor = savedTokens.edit()
        var startScreen = mutableStateOf<String>("loginScreen")
        var accessToken = savedTokens.getString("accessToken", null)


        var userToken: UserToken

        Paper.init(this)



        super.onCreate(savedInstanceState)
        setContent {

            var scenarioTemp : ScenarioData? = null
            var scenarioList = remember { mutableStateOf(scenarioTemp) }


            var bottomPanelState by remember {
                mutableStateOf(false)
            }

            if (accessToken == null){
                startScreen.value = "loginScreen"
                bottomPanelState = false
            }
            if (accessToken != null){
                startScreen.value = "mainScreen"
                bottomPanelState = true
                Log.d("MyLog", accessToken!!)
            }

            val navController = rememberNavController()

            Control_systemTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = startScreen.value,
                        modifier = Modifier
                            .weight(1.0f)
                    ){
                        composable("loginScreen"){
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Login(
                                    confirmed = {
                                        tokensEditor.apply{
                                            putString("accessToken", it.body()?.accessToken)
                                            putString("refreshToken", it.body()?.refreshToken)
                                        }.apply()
                                        accessToken = it.body()?.accessToken
                                        userToken = jwtDecoder(accessToken!!)!!
                                        LecturesServer.getTasks(
                                            userToken,
                                            onConnectionError = {
                                                showToast("Проверьте подключение к интернету")
                                            }
                                        ) {
                                            scenarioList.value = it.body()?.data
                                        }
                                        bottomPanelState = true
                                        navController.navigate("mainScreen")
                                    },
                                    onConnectionError = {
                                        showToast("Проверьте подключение к интернету")
                                    })
                            }
                        }

                        composable("mainScreen"){

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                userToken = jwtDecoder(accessToken!!)!!
                                LecturesServer.getTasks(
                                    userToken,
                                    onConnectionError = {
                                        showToast("Проверьте подключение к интернету")
                                    },
                                    confirmed = {
                                        scenarioList.value = it.body()?.data
                                    }
                                )
                                if (scenarioList.value != null){
                                    MainReportsScreen(
                                        scenarioList.value!!,
                                        startScenario = {
                                            TaskStatus(it)
                                        },
                                        onConnectionError = {
                                            showToast("Проверьте подключение к интернету")
                                        },
                                        saveScenario = {

                                        },
                                        showToast = {
                                            showToast(it)
                                        }
                                    )
                                }
                            }

                        }

                        composable("profileScreen"){
                            MainProfileScreen()
                        }
                    }
                    if (bottomPanelState){
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

    private fun showToast(text : String){
        val toast = Toast.makeText(
            this,
            text,
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

    override fun onPause() {
        super.onPause()
        SavedScenarios.saveScenarios()
    }
}
