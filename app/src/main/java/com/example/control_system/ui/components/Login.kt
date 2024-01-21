package com.example.control_system.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.BGColour

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(){
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))

    var login by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}

    Column(

    ) {
        Text(
            text = "Логин",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = interFamily,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000)
                ),
            modifier = Modifier
                .padding(bottom = 5.dp)
        )

        //Ввод логина
        TextField(
            label = {
                Text(
                    text = "Введите логин",
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontFamily = interFamily,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFBEC2C3)
                    )
                )
            },
            value = login,
            onValueChange = {
                login = it
            },
            modifier =
            Modifier
                .width(338.dp)
                .height(56.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFFBEC2C3),
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = BG2Colour
            )

        )

        Text(
            text = "Пароль",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = interFamily,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                ),
            modifier = Modifier
                .padding(top = 15.dp, bottom = 5.dp)
        )

        //Ввод пароля
        TextField(
            label = {
                Text(text = "Введите пароль",
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontFamily = interFamily,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFBEC2C3)
                    )
                )
            },
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier
                .background(
                    color = BG2Colour
                )
                .width(338.dp)
                .height(56.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFFBEC2C3),
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = BG2Colour
            )
        )

        //Забыли пароль
        Text(
            text = "Забыли пароль?",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = interFamily,
                fontWeight = FontWeight(800),
                color = Color(0xFF000000),
                ),
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 5.dp)
                .clickable {
                    /* TODO
                    Сделать метод для восстановления пароля
                    */
                }
        )
    }
}

@Preview
@Composable
fun prevLogin(){
    Login()
}