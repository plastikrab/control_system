package com.example.control_system.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.control_system.R
import com.example.control_system.view.theme.BG2Colour

@Composable
fun NavigationPannel(
    profileNavigate: () -> Unit,
    tasksNavigate: () -> Unit
){

    val profileSelected = R.drawable.profile_selected
    val profileUnselected = R.drawable.profile_unselected
    val tasksSelected = R.drawable.tasks_selected
    val tasksUnselected = R.drawable.tasks_unselected

    val profileState = mutableStateOf(profileUnselected)
    val tasksState = mutableStateOf(tasksSelected)

    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BG2Colour)
            .shadow(1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Главная
        IconButton(
            onClick = {
                profileState.value = profileUnselected
                tasksState.value = tasksSelected
                tasksNavigate() },
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .weight(1.0f)
        ) {
            Image(
                painter = painterResource(id = tasksState.value),
                contentDescription = "Tasks",
                modifier = Modifier
                    .size(54.dp, 48.dp)
            )
        }
        //Профиль
        IconButton(
            onClick = {
                profileState.value = profileSelected
                tasksState.value = tasksUnselected
                profileNavigate()
                      },
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .weight(1.0f))
        {
            Image(
                painter = painterResource(id = profileState.value),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(54.dp, 48.dp)
            )
        }
    }
}


@Preview
@Composable
private fun prev(){
    NavigationPannel(
        tasksNavigate = {

        },
        profileNavigate = {

        }
    )
}