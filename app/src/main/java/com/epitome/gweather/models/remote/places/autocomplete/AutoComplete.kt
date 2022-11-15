package com.epitome.gweather.models.remote.places.autocomplete


import com.google.gson.annotations.SerializedName

data class AutoComplete(
    @SerializedName("predictions")
    val predictions: List<Prediction> = listOf(),
    @SerializedName("status")
    val status: String = ""
)