package com.example.control_system.model.data.models.scenarioModel

import android.util.Log
import io.paperdb.Paper
import kotlinx.serialization.Serializable

@Serializable
object SavedScenarios{
    var scenarios: MutableList<com.example.control_system.model.data.models.scenarioModel.Scenario> = Paper.book().read<MutableList<com.example.control_system.model.data.models.scenarioModel.Scenario>>("scenarios", mutableListOf())!!

    fun saveScenarios(){
        Log.d("MyLog", "Сохранение сценариев")
        Paper.book().write("scenarios",
            com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios
        )
        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.forEach {
            Log.d("MyLog", it.scenario.title)
        }
    }

    fun getScenarios(){
        Log.d("MyLog", "Обновление объекта сохранённых сценариев")
        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios = Paper.book().read<MutableList<com.example.control_system.model.data.models.scenarioModel.Scenario>>("scenarios", mutableListOf())!!
        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.forEach {
            Log.d("MyLog", it.scenario.title)
        }
    }
}

