package com.example.control_system.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.example.control_system.data.model.scenarioModel.ReportAssignment


@Composable
fun CheckboxReport(
    report : ReportAssignment,
    onChange : (List<Boolean>) -> Unit
) {

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
        Text(text = report.report.choiceReports[index].text)

        }
    }

}