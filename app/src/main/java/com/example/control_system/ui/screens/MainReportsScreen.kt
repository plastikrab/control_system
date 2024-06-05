package com.example.control_system.ui.screens

import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.control_system.data.model.scenarioModel.ReportAssignment
import com.example.control_system.data.model.scenarioModel.Scenario
import com.example.control_system.data.model.scenarioModel.ScenarioData
import com.example.control_system.ui.components.TaskCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainReportsScreen(
    scenarioList: ScenarioData
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
                }
            )
        }
    }


    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Text(text = reportData?.report?.title ?: "No Data")

        }
    }

}



//@Preview
//@Composable
//private fun prev(){
//    MainReportsScreen()
//}