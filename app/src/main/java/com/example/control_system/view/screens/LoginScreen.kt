package com.example.control_system.view.screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.view.components.EnterButton
import com.example.control_system.view.components.LoginText
import com.example.control_system.view.components.LoginTextField
import com.example.control_system.view.theme.BordersColour
import com.example.control_system.view.theme.ErrorColour
import com.example.control_system.view.theme.TextBlack
import com.example.control_system.viewmodel.MainLoginViewModel
import retrofit2.Response

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Login(
    confirmed : (Response<com.example.control_system.model.data.models.Token>) -> Unit,
    onConnectionError : () -> Unit,
    viewModel: MainLoginViewModel = MainLoginViewModel()
){
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))

    var fieldsColorState by remember {
        mutableStateOf(BordersColour)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.group_8),
            contentDescription = "Decorative element",
            modifier = Modifier
                .size(206.dp, 166.dp)
                .padding(top = 20.dp)
        )

        Text(
            text = "Вход",
            style = TextStyle(
                fontSize = 58.sp,
                fontFamily = interFamily,
                fontWeight = FontWeight(850),
                color = TextBlack
            ),
            modifier = Modifier
                .padding(vertical = 20.dp)
        )

        //Ввод логина
        Column {
            LoginText(
                text = "Логин",
                fieldsColorState = fieldsColorState
            )
            LoginTextField(text = "Введите логин",
                onTextChanged = {
                    viewModel.login.value = it
                },
                false,
                fieldsColorState = fieldsColorState
            )
        }

        //Ввод пароля
        Column {
            LoginText(
                text = "Пароль",
                fieldsColorState = fieldsColorState
            )
            LoginTextField(text = "Введите пароль",
                onTextChanged = {
                    viewModel.password.value = it
                },
                true,
                fieldsColorState = fieldsColorState
            )
        }

        //Неправитный пароль или логин
        if (fieldsColorState != BordersColour){
            Text(
                text = "Неверный логин или пароль",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(400),
                    color = ErrorColour,
                ),
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 5.dp)
            )
        }


        EnterButton(
            confirmed = {
                confirmed(it)
            },
            wrongData = {
                fieldsColorState = ErrorColour
            },
            onConnectionError = {
                onConnectionError()
            }
        )


    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Preview
@Composable
fun prevLogin(){
    Login(confirmed = {

    },
        onConnectionError = {

        })
}