package com.example.control_system.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.control_system.logic.UserData
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.BGColour
import com.example.control_system.ui.theme.MainColour

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



    Column {

        //Ввод логина
        loginText(text = "Логин")
        loginTextField(text = "Введите логин"){
            UserData.login = it
        }

        //Ввод пароля
        loginText(text = "Пароль")
        loginTextField(text = "Введите пароль"){
            UserData.password = it
        }


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

        enterButton()


    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginTextField(text : String, onTextChanged: (String)-> Unit){

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))

    var fieldValue by remember {
        mutableStateOf("")
    }


    //Ввод
    OutlinedTextField(
        placeholder  = {
            Text(text = text,
                style = TextStyle(
                    fontSize = 21.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(300),
                    color = Color(0xFFBEC2C3)
                ),

            )

        },
        value = fieldValue,
        onValueChange = {
            fieldValue = it
            onTextChanged(fieldValue)
        },
        modifier = Modifier
            .background(
                color = BG2Colour
            )
            .width(338.dp)
            .height(56.dp)
        ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = BG2Colour,
            disabledLabelColor = BG2Colour,
            unfocusedLabelColor = Color(0xFFBEC2C3),
            unfocusedBorderColor = Color(0xFFBEC2C3),
            focusedBorderColor = Color(0xFFBEC2C3)
        ),
        singleLine = true,
        shape = RoundedCornerShape(10.dp),

        )

}

@Composable
fun loginText(text : String){

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))

    Text(
        text = text,
        style = TextStyle(
            fontSize = 24.sp,
            fontFamily = interFamily,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
        ),
        modifier = Modifier
            .padding(top = 15.dp, bottom = 5.dp)
    )
}

@Composable
fun enterButton(){
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))


    Button(
        onClick = {
            Log.d("MyLog", UserData.login)
            Log.d("MyLog", UserData.password)
        },
        modifier = Modifier
            .padding(top = 37.dp)
            .width(338.dp)
            .height(56.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MainColour
        )

    ) {
        Text(
            text = "Войти",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = interFamily,
                fontWeight = FontWeight(850),
                color = Color(0xFFFFFFFF)
            )
        )
    }
}

@Preview
@Composable
fun prevLogin(){
    Login()
}