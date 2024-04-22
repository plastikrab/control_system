package com.example.control_system.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.control_system.R
import com.example.control_system.ui.theme.MainColour

@Composable
fun ReportCard(
    //TODO Добавить переменные для входных данных
) {
    Card(
        modifier = Modifier
            .size(width = 147.dp, height = 233.dp),
        colors = CardDefaults.cardColors(
            containerColor = MainColour
        )
    ) {
        Column(
            modifier = Modifier
                .background(MainColour)
        ) {
            Text(
                text = "Название отчёта"
            )
            Text(
                text = "Описание отчёта"
            )
            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Plus"
            )
        }
    }
}


@Preview
@Composable
fun repCardPrev(){
    ReportCard()
}