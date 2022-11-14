package com.granson.dvtweather.data.models.places.details


import com.google.gson.annotations.SerializedName

data class Viewport(
    @SerializedName("northeast")
    val northeast: Northeast = Northeast(lat = 0.0, lng = 0.0),
    @SerializedName("southwest")
    val southwest: Southwest = Southwest(lat = 0.0, lng = 0.0),
)