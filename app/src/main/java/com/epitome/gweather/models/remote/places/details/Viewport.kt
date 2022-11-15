package com.epitome.gweather.models.remote.places.details


import com.epitome.gweather.models.remote.places.details.Northeast
import com.epitome.gweather.models.remote.places.details.Southwest
import com.google.gson.annotations.SerializedName

data class Viewport(
    @SerializedName("northeast")
    val northeast: Northeast = Northeast(lat = 0.0, lng = 0.0),
    @SerializedName("southwest")
    val southwest: Southwest = Southwest(lat = 0.0, lng = 0.0),
)