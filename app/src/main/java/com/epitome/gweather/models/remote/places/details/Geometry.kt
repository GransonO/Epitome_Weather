package com.epitome.gweather.models.remote.places.details


import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("location")
    val location: Location = Location(0.0, 0.0),
    @SerializedName("viewport")
    val viewport: Viewport = Viewport()
)