package com.example.control_system.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.ui.theme.BordersColour
import com.example.control_system.ui.theme.TextBlack

@Composable
fun LoginText(
    text : String,
    fieldsColorState : Color
){
    var color = TextBlack
    if (fieldsColorState != BordersColour){
        color = fieldsColorState
    }

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
            color = color,
        ),
        modifier = Modifier
            .padding(top = 15.dp, bottom = 5.dp)
    )
}