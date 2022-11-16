package com.epitome.gweather.models.remote.weather


import com.google.gson.annotations.SerializedName

data class Timelines(
    @SerializedName("data")
    val data: Data = Data()
)