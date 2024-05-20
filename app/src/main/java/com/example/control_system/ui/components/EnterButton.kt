package com.example.control_system.ui.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.data.model.Token
import com.example.control_system.data.objects.LoginDetails
import com.example.control_system.network.auth
import com.example.control_system.ui.theme.BG2Colour
import com.example.control_system.ui.theme.MainColour
import retrofit2.Response

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun EnterButton(
    confirmed: (Response<Token>) -> Unit,
    wrongData : () -> Unit,
    onConnectionError : () -> Unit
){
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))

    Button(
        onClick = {
            Log.d("MyLog", LoginDetails.toString())
            LoginDetails.device = "string"
            auth(
                confirmed = {
                    confirmed(it)
                },
                wrongData = {
                    wrongData()
                },
                onConnectionError = {
                    onConnectionError()
                }
            )
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