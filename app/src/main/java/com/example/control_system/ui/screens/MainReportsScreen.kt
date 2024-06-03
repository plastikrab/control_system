package com.example.control_system.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.control_system.data.model.scenarioModel.Scenario
import com.example.control_system.ui.components.TaskCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainReportsScreen(
    scenarioList: List<Scenario>
) {
    LazyColumn(
    ) {
        items(scenarioList){scenario ->
            TaskCard(scenario)
        }
    }
}



//@Preview
//@Composable
//private fun prev(){
//    MainReportsScreen()
//}