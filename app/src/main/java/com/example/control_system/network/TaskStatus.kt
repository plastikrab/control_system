package com.example.control_system.network

import android.util.Log
import com.example.control_system.data.model.scenarioModel.Scenario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun TaskStatus(
    scenario: Scenario
) {

    // Текущее время
    val currentDate = Date()
    // Форматирование времени как "год.месяц.день"
    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateText: String = dateFormat.format(currentDate)
    // Форматирование времени как "часы:минуты:секунды"
    val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val timeText: String = timeFormat.format(currentDate)


    val lecturesContainer = DefaultLectureAppContainer()

    CoroutineScope(Dispatchers.IO).launch {

        try {
            lecturesContainer.lectureRepository.startTask(
                "${dateText}T${timeText}",
                "null",
                "null",
                scenario._id
                )
        }catch (e : IOException){



        }catch (e: java.lang.IllegalStateException){
            Log.d("MyLog", "Fail to connect server")
        }
    }

//TODO Сделать нормальную геолокацию

}