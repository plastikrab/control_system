package com.example.control_system.view.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.view.theme.BGColour
import com.example.control_system.view.theme.BordersColour
import com.example.control_system.view.theme.TextBlack


@Composable
fun TextBlock(
    blocks : MutableList<com.example.control_system.model.data.models.scenarioModel.FileBlock>,
    onChangeFileBlocks : (MutableList<com.example.control_system.model.data.models.scenarioModel.FileBlock>) -> Unit
) {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))


    if (blocks.size == 0){
        blocks.add(com.example.control_system.model.data.models.scenarioModel.FileBlock("1", "", 1))
        Log.d("MyLog", "Null List")
    }

    var blocksList = mutableStateListOf<com.example.control_system.model.data.models.scenarioModel.FileBlock>()

    blocksList = blocks.toMutableStateList()



    var fieldValue by remember {
        mutableStateOf(blocksList[0].text)
    }

    OutlinedTextField(
        value = fieldValue,
        onValueChange = {
            fieldValue = it
            blocksList[0].text = fieldValue
            onChangeFileBlocks(blocksList)
        },
        placeholder = {
            Text(
                text = "Введите текст",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(500),
                    color = BordersColour
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        shape = RoundedCornerShape(14.dp),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = interFamily,
            fontWeight = FontWeight(500),
            color = TextBlack
        ),
        colors = OutlinedTextFieldDefaults.colors(
            disabledContainerColor = BGColour,
            focusedContainerColor = BGColour,
            unfocusedBorderColor = BordersColour,
            focusedBorderColor = BordersColour
        )

    )
}