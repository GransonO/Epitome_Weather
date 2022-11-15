package com.granson.dvtweather.data.models.places.details


import com.google.gson.annotations.SerializedName

data class PlaceDetails(
    @SerializedName("html_attributions")
    val htmlAttributions: List<Any> = listOf(),
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: String = ""
)