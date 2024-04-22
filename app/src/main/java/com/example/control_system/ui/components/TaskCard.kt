package com.example.control_system.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.control_system.R

@ExperimentalMaterial3Api
@Composable
fun TaskCard(
    //TODO Добавить переменные для входных данных
) {

    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    val cardHight by animateFloatAsState(
        targetValue = if (expandedState) 420f else 65f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.size(width = 410.dp, height = cardHight.dp),
                ,
        onClick = {
            expandedState = !expandedState
        }

    ) {
        Box(
            Modifier
                //.fillMaxSize()
                .background(Color.Red),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1.0f)
                ) {
                    Text(
                        text = "Name"
                    )
                    Text(
                        text = "Place"
                    )
                    Text(
                        text = "Counter"
                    )
                    //TODO Убрать хардкод
                }
                Column {
                    Text(
                        text = "Status"

                    )

                }

            }
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow",
                Modifier
                    .rotate(rotationState)
                    .padding(bottom = 1.dp)
                    .padding(end = 13.dp)
                    .size(15.dp),


            )

            if (expandedState){
                Row {
                    ReportCard()
                    ReportCard()
                    ReportCard()
                    ReportCard()
                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun taskPrev(){
    TaskCard()
}