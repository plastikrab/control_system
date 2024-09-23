package com.example.control_system.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainLoginViewModel : ViewModel() {

    var login = MutableLiveData<String>()
    val password = MutableLiveData<String>()

}