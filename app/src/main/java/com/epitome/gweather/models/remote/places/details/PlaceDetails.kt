package com.epitome.gweather.models.remote.places.details


import com.google.gson.annotations.SerializedName

data class PlaceDetails(
    @SerializedName("html_attributions")
    val htmlAttributions: List<Any> = listOf(),
    @SerializedName("result")
    val result: Result = Result(),
    @SerializedName("status")
    val status: String = ""
)