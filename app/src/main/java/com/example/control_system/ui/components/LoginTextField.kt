package com.example.control_system.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.BordersColour
import com.example.control_system.ui.theme.TextBlack

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    text : String,
    onTextChanged: (String)-> Unit,
    passwordField : Boolean,
    fieldsColorState : Color
){

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



    if (!passwordField){
        //Ввод
        OutlinedTextField(
            placeholder  = {
                Text(text = text,
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontFamily = interFamily,
                        fontWeight = FontWeight(300),
                        color = BordersColour
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
                unfocusedLabelColor = fieldsColorState,
                unfocusedBorderColor = fieldsColorState,
                focusedBorderColor = BordersColour,
                unfocusedTextColor = TextBlack,
                focusedTextColor = TextBlack
            ),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
    }

    //TODO сделать нормальные ошибки ввода

    if (passwordField){
        //Ввод
        OutlinedTextField(
            placeholder  = {
                Text(text = text,
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontFamily = interFamily,
                        fontWeight = FontWeight(300),
                        color = fieldsColorState
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
                unfocusedLabelColor = fieldsColorState,
                unfocusedBorderColor = fieldsColorState,
                focusedBorderColor = BordersColour,
                unfocusedTextColor = TextBlack,
                focusedTextColor = TextBlack
            ),
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
    }

}

@Preview
@Composable
private fun prev(){
    LoginTextField(text = "example", onTextChanged = {

    },
        false,
        BordersColour
    )
}