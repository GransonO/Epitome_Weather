package com.epitome.gweather.models.remote.weather


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("timelines")
    val timelines: List<Timeline> = listOf()
)