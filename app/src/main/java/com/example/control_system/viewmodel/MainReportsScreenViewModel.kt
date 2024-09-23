package com.example.control_system.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.control_system.model.data.models.scenarioModel.Scenario

class MainReportsScreenViewModel : ViewModel() {


    var reportData = MutableLiveData<com.example.control_system.model.data.models.scenarioModel.ReportAssignment>()
    val savedScenariosList = MutableLiveData<Scenario>()



}