package com.epitome.gweather.util

enum class PrecipitationType(id: Int, description: String) {
    NONE(0, "N/A"),
    RAIN(1, "Rain"),
    SNOW(2, "Snow"),
    FREEZING_RAIN(3, "Freezing Rain"),
    ICE_PELLETS(4, "Ice Pellets")
}