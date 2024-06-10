package com.example.control_system.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.control_system.data.model.scenarioModel.ReportAssignment
import com.example.control_system.data.model.scenarioModel.Scenario
import com.example.control_system.data.model.scenarioModel.ScenarioData
import com.example.control_system.ui.components.CheckboxReport
import com.example.control_system.ui.components.RadioButtonReport
import com.example.control_system.ui.components.TaskCard
import com.example.control_system.ui.components.TextBlock


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainReportsScreen(
    scenarioList: ScenarioData,
    startScenario : (Scenario) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val repTemp : ReportAssignment? = null
    var reportData by remember { mutableStateOf(repTemp) }

    LazyColumn(
    ) {
        Log.d("MyLog","MainScreen open")
        items(scenarioList.scenarios){scenario ->
            TaskCard(
                scenario,
                openBottomSheet = {
                    reportData = it
                    showBottomSheet = true
                },
                startScenario = {
                    startScenario(it)
                },
                finishScenario = {

                }
            )
        }
    }


    if (showBottomSheet && reportData != null) {
        ModalBottomSheet(
            modifier = Modifier
                .size(726.dp),
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            Text(
                text = reportData?.report?.title ?: "No Data",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = reportData?.report?.description ?: "No Data",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            if (reportData?.report?.type == "choiceReports"){
                Log.d("MyLog", "RadioButton opening")
                RadioButtonReport(
                    report = reportData!!,
                    selected = {
                        reportData!!.report.choiceReports.forEach{item ->
                            item.answer = item.text == it
                            reportData!!.doneStatus = true
                        }
                        reportData!!.report.choiceReports.forEach{item ->
                            Log.d("MyLog", item.text + " " + item.answer)
                        }
                    }
                )
            }

            if (reportData?.report?.type == "choiceReportsMany"){
                Log.d("MyLog", "CheckBox opening")
                CheckboxReport(
                    report = reportData!!,
                    onChange = {
                        var i = 0
                        reportData!!.report.choiceReports.forEach{item ->
                            item.answer = it[i]
                            i++
                        }
                        reportData!!.doneStatus = true
                    }
                )
            }

            if (reportData?.report?.type == "text"){
                TextBlock(
                    blocks = reportData!!.report.fileBlocks.toMutableList(),
                    onChangeFileBlocks = {
                        reportData!!.report.fileBlocks = it
                        reportData!!.doneStatus = true
                    }

                )
            }
        }
    }

}



//@Preview
//@Composable
//private fun prev(){
//    MainReportsScreen()
//}