package com.example.control_system.model.data.models

import com.google.gson.annotations.SerializedName

data class LectureRequestData(
    @SerializedName("userId ") val userId : Int,
    @SerializedName("limit ") val limit : Int,
    @SerializedName("page ") val page : Int
)
