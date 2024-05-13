package com.example.control_system.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportBottomSheet() {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet){

        ModalBottomSheet(onDismissRequest = {
            /*TODO*/
        },
            //sheetState = SheetState()
        ) {
            Text(text = "Example")
            Button(onClick = {
                showBottomSheet = false
            }) {

            }
        }
    }
}


@Preview
@Composable
fun prevTaskBottomSheet(){
    ReportBottomSheet()
}