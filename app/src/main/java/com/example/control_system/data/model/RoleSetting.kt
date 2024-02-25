package com.example.control_system.data.model

data class RoleSetting(
    val id: Int,
    val editUsers: Boolean,
    val editLectures: Boolean,
    val assignPerson: Boolean,
    val accessToReports: Boolean
)
