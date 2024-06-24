package com.example.control_system.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.control_system.data.model.scenarioModel.FileBlock
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.BGColour
import com.example.control_system.ui.theme.BordersColour
import com.example.control_system.ui.theme.TextBlack


@Composable
fun TextBlock(
    blocks : MutableList<FileBlock>,
    onChangeFileBlocks : (MutableList<FileBlock>) -> Unit
) {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))


    if (blocks.size == 0){
        blocks.add(FileBlock("1","",1))
        Log.d("MyLog", "Null List")
    }

    var blocksList = mutableStateListOf<FileBlock>()

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