package com.example.control_system.data.model.scenarioModel

import android.util.Log
import io.paperdb.Paper
import kotlinx.serialization.Serializable

@Serializable
object SavedScenarios{
    var scenarios: MutableList<Scenario> = Paper.book().read<MutableList<Scenario>>("scenarios", mutableListOf())!!

    fun saveScenarios(){
        Log.d("MyLog", "Сохранение сценариев")
        Paper.book().write("scenarios", scenarios)
        scenarios.forEach {
            Log.d("MyLog", it.scenario.title)
        }
    }

    fun getScenarios(){
        Log.d("MyLog", "Обновление объекта сохранённых сценариев")
        scenarios = Paper.book().read<MutableList<Scenario>>("scenarios", mutableListOf())!!
        scenarios.forEach {
            Log.d("MyLog", it.scenario.title)
        }
    }
}

