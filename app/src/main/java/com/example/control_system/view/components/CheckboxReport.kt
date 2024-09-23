package com.example.control_system.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
import com.example.control_system.view.theme.TextBlack


@Composable
fun CheckboxReport(
    report : com.example.control_system.model.data.models.scenarioModel.ReportAssignment,
    onChange : (List<Boolean>) -> Unit
) {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))


    var checkedStates = remember { mutableStateListOf(false) }
    report.report.choiceReports.forEachIndexed() { index, it ->
        if (checkedStates.size == index + 1){
            checkedStates[index] = it.answer
        }
        if (checkedStates.size < index + 1){
            checkedStates.add(index, it.answer)
        }
    }

    checkedStates.forEachIndexed{index, checked ->

    Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
        
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checkedStates[index] = isChecked
                onChange(checkedStates)
            }
        )
        Text(
            text = report.report.choiceReports[index].text,
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