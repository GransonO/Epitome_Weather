package com.epitome.gweather.models.remote.weather


import com.google.gson.annotations.SerializedName

data class Values(
    @SerializedName("temperature")
    val temperature: Double
)