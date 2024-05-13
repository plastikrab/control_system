package com.example.control_system.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.MainColour

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportCard(
    //TODO Добавить переменные для входных данных
) {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))


    Card(
        modifier = Modifier
            .size(width = 167.dp, height = 233.dp)
            .padding(horizontal = 10.dp)
            .shadow(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MainColour
        ),
        onClick = {
            Log.d("MyLog", "Clicked")
            //TODO Добавление информации в отчёт
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(MainColour)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Название части отчёта",
                    color = BG2Colour,
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = interFamily,
                        fontWeight = FontWeight(650),
                        color = BG2Colour
                    ),
                    modifier = Modifier
                        .padding(start = 7.dp)
                        .padding(top = 1.dp)
                )
                Text(
                    text = "Описание отчёта",
                    color = BG2Colour,
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontFamily = interFamily,
                        fontWeight = FontWeight(350),
                        color = BG2Colour
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                //TODO Убрать хардкод и сделать стиль


            }
            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Plus",
                modifier = Modifier
                    .size(53.dp)
                    .align(Alignment.Center)
            )
        }

    }
}


@Preview
@Composable
fun repCardPrev(){
    ReportCard()
}