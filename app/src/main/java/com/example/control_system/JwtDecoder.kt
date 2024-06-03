package com.example.control_system

import android.util.Base64
import com.auth0.android.jwt.JWT
import com.example.control_system.data.model.RoleSettings
import com.example.control_system.data.model.UserToken
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineStart
import java.nio.charset.StandardCharsets
import java.util.Date
import kotlin.io.encoding.ExperimentalEncodingApi

fun JwtDecoder(token: String) {

    try {
        val jwt = JWT.decode(token)
        val login = jwt.getClaim("login").asString()
        val sub = jwt.getClaim("sub").asString()
        val iat = Date(jwt.getClaim("iat").asLong())
        val exp = Date(jwt.getClaim("exp").asLong())

        val roleSettings = RoleSettings(
            jwt.getClaim("roleSettings").asJsonObject().get("id").asInt(),
            jwt.getClaim("roleSettings").asJsonObject().get("user").asBoolean(),
            jwt.getClaim("roleSettings").asJsonObject().get("manager").asBoolean(),
            jwt.getClaim("roleSettings").asJsonObject().get("analyst").asBoolean(),
            jwt.getClaim("roleSettings").asJsonObject().get("administrator").asBoolean()
        )

        val userToken = UserToken(login, roleSettings, sub, iat, exp)
        println("Создан объект UserToken: $userToken")
    } catch (e: JWTDecodeException) {
        println("Ошибка при парсинге JWT токена: ${e.message}")
    }
}