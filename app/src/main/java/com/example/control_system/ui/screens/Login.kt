package com.example.control_system.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import com.example.control_system.R
import com.example.control_system.data.objects.LoginDetails
import com.example.control_system.ui.components.EnterButton
import com.example.control_system.ui.components.LoginText
import com.example.control_system.ui.components.LoginTextField

@Composable
fun Login(){

    Column {

        //Ввод логина
        LoginText(text = "Логин")
        LoginTextField(text = "Введите логин",
            onTextChanged = {
                LoginDetails.login = it
            })

        //Ввод пароля
        LoginText(text = "Пароль")
        LoginTextField(text = "Введите пароль",
            onTextChanged = {
                LoginDetails.password = it
            })

        EnterButton()


    }
}

@Preview
@Composable
fun prevLogin(){
    Login()
}