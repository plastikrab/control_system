package com.example.control_system.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.data.model.scenarioModel.ReportAssignment
import com.example.control_system.ui.theme.RadioButtonColour
import com.example.control_system.ui.theme.TextBlack


@Composable
fun RadioButtonReport(
    report : ReportAssignment,
    selected : (String) -> Unit
) {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))



    if (report.report.choiceReports != null){
        Log.d("MyLog","Not null")


        var chosenButton = 0
        report.report.choiceReports.forEach {
            if (it.answer){
                chosenButton = report.report.choiceReports.indexOf(it)
            }
        }

        val radioOptions = report.report.choiceReports
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[chosenButton] ) }

        Column {


            radioOptions.forEach { rButton ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (rButton == selectedOption),
                            onClick = {
                                onOptionSelected(rButton)
                                selected(rButton.text)
                            }
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (rButton == selectedOption),
                        onClick = {
                            onOptionSelected(rButton)
                            selected(rButton.text)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = RadioButtonColour,
                            unselectedColor = RadioButtonColour
                        )
                    )
                    Text(
                        text = rButton.text,
                        modifier = Modifier.padding(start = 5.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = interFamily,
                            fontWeight = FontWeight(700),
                            color = TextBlack
                        )
                    )
                }
            }
        }
    }
    else{
        Log.d("MyLog","Error")
    }

}

//@Preview
//@Composable
//private fun prev(){
//    RadioButtonReport()
//}