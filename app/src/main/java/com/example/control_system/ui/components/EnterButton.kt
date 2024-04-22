package com.example.control_system.ui.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.data.model.UserData
import com.example.control_system.data.model.UserLogin
import com.example.control_system.data.objects.LoginDetails
import com.example.control_system.network.AppContainer
import com.example.control_system.network.DefaultAppContainer
import com.example.control_system.network.auth
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.MainColour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

@Composable
fun EnterButton(){
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))
    lateinit var container : AppContainer

    Button(
        onClick = {
            Log.d("MyLog", LoginDetails.toString())
            LoginDetails.device = "string"
            auth()
        },
        modifier = Modifier
            .padding(top = 37.dp)
            .width(338.dp)
            .height(56.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MainColour
        )

    ) {
        Text(
            text = "Войти",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = interFamily,
                fontWeight = FontWeight(850),
                color = BG2Colour
            )
        )
    }
}