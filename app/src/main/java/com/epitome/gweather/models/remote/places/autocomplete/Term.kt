package com.granson.dvtweather.data.models.places.autocomplete


import com.google.gson.annotations.SerializedName

data class Term(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("value")
    val value: String
)