package com.example.control_system.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.MainActivity
import com.example.control_system.R
import com.example.control_system.data.model.scenarioModel.ReportAssignment
import com.example.control_system.data.model.scenarioModel.Scenario
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.MainColour
import com.example.control_system.ui.theme.TextBlack

@ExperimentalMaterial3Api
@Composable
fun TaskCard(
    scenario: Scenario,
    openBottomSheet : (ReportAssignment) -> Unit,
    startScenario : (Scenario) -> Unit,
    finishScenario : (Scenario) -> Unit,
    showToast : (String) -> Unit
) {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))


    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    var buttonName by remember {
        mutableStateOf("")
    }
    var statusName by remember {
        mutableStateOf("")
    }
    when(scenario.status){
        "ToDo" -> {
            buttonName = "Начать"
            statusName = "Ожидание начала"
        }
        "inProgress" -> {
            buttonName = "Закончить"
            statusName = "В процессе"
        }
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = TweenSpec(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(vertical = 10.dp)
            .padding(horizontal = 10.dp)
            .shadow(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = BG2Colour
        ),
        onClick = {
            expandedState = !expandedState
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(start = 10.dp)
                ) {
                    //Название
                    Text(
                        text = scenario.scenario.title,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = interFamily,
                            fontWeight = FontWeight(950),
                            color = TextBlack
                        )
                    )
                    //Место
                    Text(
                        text = "Место",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = interFamily,
                            fontWeight = FontWeight(550),
                            color = TextBlack
                        )
                    )
                    //Количество сданных отчётов
                    Text(
                        text = "Количество сданных отчётов",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = interFamily,
                            fontWeight = FontWeight(950),
                            color = TextBlack
                        )
                    )
                    //TODO Убрать хардкод и настроить стиль
                }
                Column {
                    Text(
                        text = statusName,
                        modifier = Modifier
                            .padding(end = 10.dp),
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = interFamily,
                            fontWeight = FontWeight(950),
                            color = TextBlack
                        )
                    )

                }
            }
            if (expandedState)
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                            .padding(bottom = 30.dp)
                    ) {
                        items(scenario.reportsAssigned.size){
                            ReportCard(
                                scenario.reportsAssigned[it],
                                onClick = {
                                    if (scenario.status == "inProgress"){
                                        openBottomSheet(it)
                                    }else{
                                        showToast("Сначала начините сценарий")
                                    }

                                }
                            )
                        }
                    }

                    Button(
                        modifier = Modifier
                            .size(width = 292.dp, height = 51.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MainColour
                        ),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {
                            if (scenario.status == "ToDo"){
                                scenario.status = "inProgress"
                                buttonName = "Закончить"
                                statusName = "В процессе"
                                startScenario(scenario)
                            }
                            if (scenario.status == "inProgress"){

                            }

                    }
                    ) {
                        Text(
                            text = buttonName,
                            color = BG2Colour,
                            style = TextStyle(
                                fontSize = 21.sp,
                                fontFamily = interFamily,
                                fontWeight = FontWeight(750),
                                color = BG2Colour
                            )
                        )
                    }
                }
            }

            Row(
                Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Image(painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Arrow",
                    Modifier
                        .rotate(rotationState)
                        //.padding(bottom = 2.dp)
                        .padding(end = 13.dp)
                        .padding(start = 13.dp)
                        .size(15.dp),

                    )
            }

        }

    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//private fun prev(){
//    Column(
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//    ) {
//        TaskCard()
//        TaskCard()
//        TaskCard()
//    }
//
//}