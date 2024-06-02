package com.example.control_system.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.control_system.data.model.Scenario
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