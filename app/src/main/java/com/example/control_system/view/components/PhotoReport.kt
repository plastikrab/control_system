package com.example.control_system.view.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.view.theme.BG2Colour
import com.example.control_system.view.theme.MainColour


@Composable
fun PhotoReport(

) {
    Column {
        val provider = GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        )
        val interFont = GoogleFont("Inter")
        val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))

        Button(
            onClick = {
                Log.d("MyLog", "Фото")
            },
            modifier = Modifier
                .padding(top = 17.dp)
                .width(209.dp)
                .height(56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColour
            )

        ) {
            Text(
                text = "Добавить фото",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(850),
                    color = BG2Colour
                )
            )
        }
        //TODO Сделать Grid с фото
    }
}

@Preview
@Composable
private fun prev(){
    PhotoReport()
}