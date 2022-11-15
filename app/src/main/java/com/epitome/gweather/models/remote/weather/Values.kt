package com.epitome.gweather.models.remote.weather

import com.google.gson.annotations.SerializedName

data class Values(
    @SerializedName("temperature")
    val temperature: Double? = null,
    @SerializedName("cloudCover")
    val cloudCover: Double? = null,
    @SerializedName("humidity")
    val humidity: Double? = null,
    @SerializedName("precipitationType")
    val precipitationType: Double? = null,
    @SerializedName("precipitationIntensity")
    val precipitationIntensity: Double? = null,
    @SerializedName("weatherCode")
    val weatherCode: Int? = null,
    @SerializedName("windSpeed")
    val windSpeed: Double? = null,
    @SerializedName("visibility")
    val visibility: Double? = null,
    @SerializedName("dateTime")
    var dateTime: String? = null,
)