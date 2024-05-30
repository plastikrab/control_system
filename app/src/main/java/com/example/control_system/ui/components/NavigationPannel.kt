package com.example.control_system.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.control_system.R
import com.example.control_system.ui.theme.BG2Colour

@Composable
fun NavigationPannel(
    selectionStatus : Int
){

    val profileSelected = R.drawable.profile_selected
    val profileUnselected = R.drawable.profile_unselected
    val tasksSelected = R.drawable.tasks_selected
    val tasksUnselected = R.drawable.tasks_unselected

    var profileState = profileSelected
    var tasksState = tasksUnselected

    if (selectionStatus == 1){
        profileState = profileUnselected
        tasksState = tasksSelected
    }
    if (selectionStatus == 2){
        profileState = profileSelected
        tasksState = tasksUnselected
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BG2Colour)
            .shadow(1.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = tasksState),
            contentDescription = "Tasks",
            modifier = Modifier
                .size(54.dp, 48.dp)
                .padding(horizontal = 22.dp)
                .weight(1.0f)
        )
        Image(
            painter = painterResource(id = profileState),
            contentDescription = "Profile",
            modifier = Modifier
                .size(54.dp, 48.dp)
                .padding(horizontal = 22.dp)
                .weight(1.0f)
        )
    }
}

@Preview
@Composable
private fun prev(){
    NavigationPannel(selectionStatus = 2)
}