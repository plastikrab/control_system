package com.example.control_system.data

import com.example.control_system.data.model.scenarioModel.ReportAssignment
import com.example.control_system.data.model.scenarioModel.Scenario
import io.paperdb.Paper
import java.io.File

object ScenariosSaver{

    private val scenariosJson = File("scenarios.txt")

    fun updateScenarios(scenarios : MutableList<Scenario>, reportData : ReportAssignment){
        scenarios.forEach { scenario ->
            scenario.reportsAssigned.forEach {
                if (it._id == reportData._id){
                    scenario.reportsAssigned[scenario.reportsAssigned.indexOf(it)] = reportData
                }
            }
        }
        Paper.book().write("scenarios", scenarios)
    }

    fun saveScenarios(scenarios : MutableList<Scenario>){
        Paper.book().write("scenarios", scenarios)
    }



}