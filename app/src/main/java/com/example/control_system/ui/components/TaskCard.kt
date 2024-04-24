package com.example.control_system.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = TweenSpec(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
        }

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
            //TODO Убрать хардкод и настроить стиль
            }
            Column {
                Text(
                    text = "Status"
                )
                Image(painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Arrow",
                    Modifier
                        .rotate(rotationState)
                        .padding(top = 18.dp)
                        .padding(start = 13.dp)
                        .size(15.dp)
                )
            }
        }
        if (expandedState)
        {
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                ReportCard()
                ReportCard()
                ReportCard()
                ReportCard()
                ReportCard()
                ReportCard()
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